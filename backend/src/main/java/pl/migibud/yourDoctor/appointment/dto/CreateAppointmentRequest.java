package pl.migibud.yourDoctor.appointment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppointmentRequest {
    @NotNull
    private Long doctorId;
    @NotNull
    private Long addressId;
    @NotNull(message = "Date of appointment must be set")
    private LocalDate dateOfAppointment;
    @NotNull(message = "Time of appointment must be set")
    private LocalTime timeOfAppointment;
}
