package pl.migibud.yourDoctor.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppointmentRegistrationRequest {
    @NotNull
    private Long appointmentId;
    @NotNull
    private Long userId;

}
