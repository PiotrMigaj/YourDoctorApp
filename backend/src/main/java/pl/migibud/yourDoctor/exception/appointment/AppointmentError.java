package pl.migibud.yourDoctor.exception.appointment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AppointmentError {

	APPOINTMENT_NOT_FOUND("Appointment does not exists"),
	APPOINTMENT_ALREADY_CREATED("Appointment already created"),
	APPOINTMENT_ALREADY_TAKEN("Appointment already taken"),
	DOCTOR_MAKE_APPOINTMENT_TO_HIMSELF("Doctor cannot make an appointment to himself"),
	DATE_APPOINTMENT_IS_INVALID("You must create appointment at least one day forward");

	private final String message;
}
