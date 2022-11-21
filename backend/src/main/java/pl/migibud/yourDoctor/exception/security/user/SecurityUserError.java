package pl.migibud.yourDoctor.exception.security.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SecurityUserError {

	SECURITY_USER_NOT_FOUND("Security user does not exists"),
	SECURITY_USER_ID_DOES_NOT_MATCH("Invalid user id sent in the request"),
	SECURITY_USER_ALREADY_EXISTS("User already registered");

	private final String message;
}
