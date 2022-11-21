package pl.migibud.yourDoctor.exception.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.migibud.yourDoctor.exception.doctor.DoctorError;

@AllArgsConstructor
@Getter
public class AddressException extends RuntimeException{

	private AddressError addressError;
}
