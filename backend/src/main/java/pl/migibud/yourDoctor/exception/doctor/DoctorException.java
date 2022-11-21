package pl.migibud.yourDoctor.exception.doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DoctorException extends RuntimeException{

	private DoctorError doctorError;
}
