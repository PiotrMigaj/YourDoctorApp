package pl.migibud.yourDoctor.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.migibud.yourDoctor.address.dto.AddressDto;
import pl.migibud.yourDoctor.doctor.dto.DoctorForUserDto;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentForUserDto {
    private Long id;
    private LocalDate dateOfAppointment;
    private LocalTime timeOfAppointment;
    private DoctorForUserDto doctor;
    private AddressDto address;
    private boolean taken;
}
