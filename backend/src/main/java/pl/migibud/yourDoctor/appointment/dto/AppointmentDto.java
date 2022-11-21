package pl.migibud.yourDoctor.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    private Long id;
    private LocalDate dateOfAppointment;
    private LocalTime timeOfAppointment;
    private boolean taken;

}
