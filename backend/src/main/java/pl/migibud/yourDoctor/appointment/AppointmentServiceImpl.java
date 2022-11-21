package pl.migibud.yourDoctor.appointment;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.migibud.yourDoctor.address.Address;
import pl.migibud.yourDoctor.appointment.dto.*;
import pl.migibud.yourDoctor.appointment.mapper.AppointmentMapper;
import pl.migibud.yourDoctor.appointment.repo.AppointmentRepository;
import pl.migibud.yourDoctor.appointment.vo.AppointmentEvent;
import pl.migibud.yourDoctor.doctor.Doctor;
import pl.migibud.yourDoctor.doctor.repo.DoctorRepository;
import pl.migibud.yourDoctor.domain.event.DomainEventPublisher;
import pl.migibud.yourDoctor.exception.address.AddressError;
import pl.migibud.yourDoctor.exception.address.AddressException;
import pl.migibud.yourDoctor.exception.appointment.AppointmentError;
import pl.migibud.yourDoctor.exception.appointment.AppointmentException;
import pl.migibud.yourDoctor.exception.doctor.DoctorError;
import pl.migibud.yourDoctor.exception.doctor.DoctorException;
import pl.migibud.yourDoctor.exception.user.AppUserError;
import pl.migibud.yourDoctor.exception.user.AppUserException;
import pl.migibud.yourDoctor.user.AppUser;
import pl.migibud.yourDoctor.user.repo.AppUserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AppointmentServiceImpl implements AppointmentService {

    private static final int DAYS_TO_ADD = 1;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final AppUserRepository appUserRepository;
    private final AppointmentMapper appointmentMapper;
    private final DomainEventPublisher domainEventPublisher;


    @Override
    public Page<AppointmentDto> getAllAppointments(PageRequest pageRequest) {
        Page<Appointment> appointmentPage = appointmentRepository.findAll(pageRequest);
        List<AppointmentDto> collect = appointmentPage.getContent().stream()
                .map(appointmentMapper::mapAppointmentToAppointmentDto)
                .collect(Collectors.toList());
        return new PageImpl<>(collect,appointmentPage.getPageable(),appointmentPage.getTotalElements());
    }

    @Override
    public Page<AppointmentForDoctorDto> getAllAppointmentsForDoctorWithId(PageRequest pageRequest, Long doctorId) {
        Page<Appointment> appointmentPage = appointmentRepository.findByDoctor_IdWithValidDateOfAppointment(doctorId,LocalDate.now(),pageRequest);
        List<AppointmentForDoctorDto> collect = appointmentPage.getContent().stream()
                .filter(appointment -> LocalDateTime.of(appointment.getDateOfAppointment(),appointment.getTimeOfAppointment()).isAfter(LocalDateTime.now()))
                .map(appointmentMapper::mapAppointmentToAppointmentForDoctorDto)
                .collect(Collectors.toList());
        return new PageImpl<>(collect,appointmentPage.getPageable(),appointmentPage.getTotalElements());
    }

    @Override
    public Page<AppointmentForUserDto> getAllAvailableAppointmentsForDoctorWithId(PageRequest pageRequest, Long doctorId) {
        Page<Appointment> appointmentPage = appointmentRepository.findByDoctor_IdWithValidDateTimeOfAppointmentAndNotTaken(doctorId,LocalDate.now(),LocalTime.now(),pageRequest);
        List<AppointmentForUserDto> collect = appointmentPage.getContent().stream()
                .filter(appointment -> LocalDateTime.of(appointment.getDateOfAppointment(),appointment.getTimeOfAppointment()).isAfter(LocalDateTime.now()))
                .map(appointmentMapper::mapAppointmentToAppointmentForUserDto)
                .collect(Collectors.toList());
        return new PageImpl<>(collect,appointmentPage.getPageable(),appointmentPage.getTotalElements());
    }

    @Override
    public Page<AppointmentForUserDto> getAllAppointmentsForAppUserWithId(PageRequest pageRequest, Long userId) {
        Page<Appointment> appointmentPage = appointmentRepository.findByAppUserId_IdWithValidDateOfAppointment(userId,LocalDate.now(),pageRequest);
        List<AppointmentForUserDto> collect = appointmentPage.getContent().stream()
                .filter(appointment -> LocalDateTime.of(appointment.getDateOfAppointment(),appointment.getTimeOfAppointment()).isAfter(LocalDateTime.now()))
                .map(appointmentMapper::mapAppointmentToAppointmentForUserDto)
                .collect(Collectors.toList());
        return new PageImpl<>(collect,appointmentPage.getPageable(),appointmentPage.getTotalElements());
    }

    @Override
    @Transactional
    public AppointmentDto addAppointment(CreateAppointmentRequest createAppointmentRequest) {

        Long doctorId = createAppointmentRequest.getDoctorId();
        Long addressId = createAppointmentRequest.getAddressId();
        LocalDate dateOfAppointment = createAppointmentRequest.getDateOfAppointment();
        log.info("Date of appointment: {}",dateOfAppointment);
        LocalTime timeOfAppointment = createAppointmentRequest.getTimeOfAppointment();
        log.info("Time of appointment: {}",timeOfAppointment);
        validateDateTimeOfAppointment(dateOfAppointment, timeOfAppointment);

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorException(DoctorError.DOCTOR_NOT_FOUND));

        Address address = doctor.getAddresses()
                .stream()
                .filter(a -> a.getId().equals(addressId))
                .findFirst()
                .orElseThrow(() -> new AddressException(AddressError.ADDRESS_NOT_FOUND_FOR_GIVEN_DOCTOR));

        Appointment appointment = new Appointment(dateOfAppointment, timeOfAppointment, address, doctor);

        return appointmentMapper.mapAppointmentToAppointmentDto(appointmentRepository.save(appointment));
    }

    private void validateDateTimeOfAppointment(LocalDate dateOfAppointment, LocalTime timeOfAppointment) {
        if(dateOfAppointment.isBefore(LocalDate.now().plusDays(DAYS_TO_ADD))){
            throw new AppointmentException(AppointmentError.DATE_APPOINTMENT_IS_INVALID);
        }
        appointmentRepository.findByDateOfAppointmentAndTimeOfAppointment(dateOfAppointment,timeOfAppointment)
                .ifPresent(
                        appointment -> {
                            throw new AppointmentException(AppointmentError.APPOINTMENT_ALREADY_CREATED);
                        }
                );
    }

    @Override
    @Transactional
    public AppointmentDto registerUserToAppointment(CreateAppointmentRegistrationRequest request) {

        //TODO: Consider if patient wants to make another appointment when already has registered appointment at the same time

        log.info("appointmentId: {}",request.getAppointmentId());

        Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                .orElseThrow(() -> new AppointmentException(AppointmentError.APPOINTMENT_NOT_FOUND));

        AppUser appUser = appUserRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppUserException(AppUserError.USER_NOT_FOUND));

        validateAppointmentBeforeUserRegistration(request.getUserId(), appointment);

        appointment.setAppUser(appUser);
        appointment.setRegistrationTime(LocalDateTime.now());
        appointment.setTaken(true);

        domainEventPublisher.publish(new AppointmentEvent(
                new AppointmentEvent.Data(appointment)
        ));

        return appointmentMapper.mapAppointmentToAppointmentDto(appointment);
    }

    private void validateAppointmentBeforeUserRegistration(Long userId, Appointment appointment) {
        if (appointment.getDoctor().getId().equals(userId)){
            throw new AppointmentException(AppointmentError.DOCTOR_MAKE_APPOINTMENT_TO_HIMSELF);
        }
        if(appointment.isTaken()){
            throw new AppointmentException(AppointmentError.APPOINTMENT_ALREADY_TAKEN);
        }
    }
}
