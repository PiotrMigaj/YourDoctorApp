package pl.migibud.yourDoctor.exception.medical.specialization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.migibud.yourDoctor.exception.doctor.DoctorError;

@AllArgsConstructor
@Getter
public class SpecializationException extends RuntimeException{

	private SpecializationError specializationError;
}
