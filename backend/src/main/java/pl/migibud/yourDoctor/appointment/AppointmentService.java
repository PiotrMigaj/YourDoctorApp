package pl.migibud.yourDoctor.appointment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pl.migibud.yourDoctor.appointment.dto.*;

public interface AppointmentService {

    AppointmentDto addAppointment(CreateAppointmentRequest createAppointmentRequest);
    AppointmentDto registerUserToAppointment(CreateAppointmentRegistrationRequest request);

    Page<AppointmentDto> getAllAppointments(PageRequest pageRequest);
    Page<AppointmentForDoctorDto> getAllAppointmentsForDoctorWithId(PageRequest pageRequest, Long doctorId);
    Page<AppointmentForUserDto> getAllAvailableAppointmentsForDoctorWithId(PageRequest pageRequest,Long doctorId);

    Page<AppointmentForUserDto> getAllAppointmentsForAppUserWithId(PageRequest pageRequest, Long userId);
}
