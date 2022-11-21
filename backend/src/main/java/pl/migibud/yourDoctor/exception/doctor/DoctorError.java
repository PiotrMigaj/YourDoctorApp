package pl.migibud.yourDoctor.exception.doctor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DoctorError {

	DOCTOR_NOT_FOUND("Doctor does not exists");

	private final String message;
}
