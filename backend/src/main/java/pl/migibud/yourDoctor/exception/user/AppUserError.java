package pl.migibud.yourDoctor.exception.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AppUserError {

	USER_NOT_FOUND("User does not exists");

	private final String message;
}
