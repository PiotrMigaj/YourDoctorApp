package pl.migibud.yourDoctor.exception.security.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.migibud.yourDoctor.exception.user.AppUserError;

@AllArgsConstructor
@Getter
public class SecurityUserException extends RuntimeException{

	private SecurityUserError securityUserError;
}
