package pl.migibud.yourDoctor.doctor;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.migibud.yourDoctor.address.Address;
import pl.migibud.yourDoctor.address.AddressService;
import pl.migibud.yourDoctor.doctor.dto.CreateDoctorRequest;
import pl.migibud.yourDoctor.doctor.dto.DoctorDto;
import pl.migibud.yourDoctor.doctor.mapper.DoctorMapper;
import pl.migibud.yourDoctor.doctor.repo.DoctorRepository;
import pl.migibud.yourDoctor.domain.event.DomainEventPublisher;
import pl.migibud.yourDoctor.user.vo.AppUserEvent;
import pl.migibud.yourDoctor.exception.doctor.DoctorError;
import pl.migibud.yourDoctor.exception.doctor.DoctorException;
import pl.migibud.yourDoctor.medical.specialization.MedicalSpecialization;
import pl.migibud.yourDoctor.medical.specialization.SpecializationService;
import pl.migibud.yourDoctor.security.user.SecurityUser;
import pl.migibud.yourDoctor.security.user.SecurityUserService;
import pl.migibud.yourDoctor.user.AppUser;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final SecurityUserService securityUserService;
    private final DomainEventPublisher domainEventPublisher;
    private final AddressService addressService;
    private final SpecializationService specializationService;

    @Override
    @Transactional
    public DoctorDto registerDoctor(CreateDoctorRequest createDoctorRequest) {

        SecurityUser securityUser = securityUserService.createSecurityUserForDoctor(
                createDoctorRequest.getEmail(),
                createDoctorRequest.getPassword()
        );
        AppUser appUser = new AppUser(
                securityUser.getId(),
                createDoctorRequest.getFirstName(),
                createDoctorRequest.getLastName(),
                securityUser
        );
        MedicalSpecialization medicalSpecialization = specializationService.createMedicalSpecialization(createDoctorRequest.getCreateSpecializationRequest());
        Address address = addressService.createAddress(createDoctorRequest.getCreateAddressRequest());
        Doctor doctor = new Doctor(
                securityUser.getId(),
                createDoctorRequest.getPhoneNumber(),
                Set.of(medicalSpecialization),
                Set.of(address),
                appUser
        );
        publishRegistrationEvent(securityUser, appUser);
        return doctorMapper.mapDoctorToDoctorDto(doctorRepository.save(doctor));
    }

    private void publishRegistrationEvent(SecurityUser securityUser, AppUser appUser) {
        domainEventPublisher.publish(new AppUserEvent(new AppUserEvent.Data(
                appUser.getFirstName(),
                appUser.getLastName(),
                securityUser.getEmail()
        )));
    }

    @Override
    public Page<DoctorDto> getAllDoctors(PageRequest pageRequest, String city, String specializationName) {

        MedicalSpecialization.Specialization specialization = MedicalSpecialization.Specialization.getSpecialization(specializationName);

        if (specialization == null && isQueryParamValid(specializationName)) {
            log.info("Return empty list, because of invalid specialization");
            return new PageImpl<>(Collections.emptyList(), pageRequest, 0L);
        }
        if (isQueryParamValid(city) && (specialization != null)) {
            Page<Doctor> doctorPage = doctorRepository.findBySpecializations_SpecializationAndAddresses_CityContainingIgnoreCase(specialization, city, pageRequest);
            log.info("Find doctors by city and specialization");
            return new PageImpl<>(getDoctorDtoList(doctorPage), doctorPage.getPageable(), doctorPage.getTotalElements());
        }
        if (isQueryParamValid(city) && (specialization == null)) {
            Page<Doctor> doctorPage = doctorRepository.findByAddresses_CityContainingIgnoreCase(city, pageRequest);
            log.info("Find doctors by city");
            return new PageImpl<>(getDoctorDtoList(doctorPage), doctorPage.getPageable(), doctorPage.getTotalElements());
        }
        if (!isQueryParamValid(city) && (specialization != null)) {
            Page<Doctor> doctorPage = doctorRepository.findBySpecializations_Specialization(specialization, pageRequest);
            log.info("Find doctors by specialization");
            return new PageImpl<>(getDoctorDtoList(doctorPage), doctorPage.getPageable(), doctorPage.getTotalElements());
        }
        Page<Doctor> doctorPage = doctorRepository.findAll(pageRequest);
        log.info("Find all doctors");
        return new PageImpl<>(getDoctorDtoList(doctorPage), doctorPage.getPageable(), doctorPage.getTotalElements());
    }

    private List<DoctorDto> getDoctorDtoList(Page<Doctor> doctorsPage) {
        List<DoctorDto> collect = doctorsPage.getContent().stream()
                .map(doctorMapper::mapDoctorToDoctorDto)
                .collect(Collectors.toList());
        return collect;
    }

    private boolean isQueryParamValid(String param) {
        return param != null && !param.isBlank();
    }

    @Override
    public DoctorDto getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId)
                .map(doctorMapper::mapDoctorToDoctorDto)
                .orElseThrow(() -> new DoctorException(DoctorError.DOCTOR_NOT_FOUND));
    }

    @Override
    public List<DoctorDto> getRecentlyJoinedDoctors() {
        return doctorRepository.findTop3ByOrderByCreatedAtDesc().stream()
                .map(doctorMapper::mapDoctorToDoctorDto)
                .collect(Collectors.toList());
    }
}
