package pl.migibud.yourDoctor.exception.appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.migibud.yourDoctor.exception.address.AddressError;

@AllArgsConstructor
@Getter
public class AppointmentException extends RuntimeException{

	private AppointmentError appointmentError;
}
