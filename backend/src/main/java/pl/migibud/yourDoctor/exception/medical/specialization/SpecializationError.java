package pl.migibud.yourDoctor.exception.medical.specialization;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SpecializationError {

	MEDICAL_SPECIALIZATION_NOT_FOUND("Medical specialization does not exists");

	private final String message;
}
