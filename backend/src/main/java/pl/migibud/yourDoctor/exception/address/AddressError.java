package pl.migibud.yourDoctor.exception.address;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AddressError {

	ADDRESS_NOT_FOUND("Address does not exists"),
	ADDRESS_NOT_FOUND_FOR_GIVEN_DOCTOR("There is no address for given doctor");

	private final String message;
}
