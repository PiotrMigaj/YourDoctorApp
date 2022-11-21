package pl.migibud.yourDoctor.auth;

import lombok.*;

@Getter(value = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class AuthenticationRequest {
    private String login;
    private String pass;
}
