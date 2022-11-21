package pl.migibud.yourDoctor.exception.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AppUserException extends RuntimeException{

	private AppUserError appUserError;
}
