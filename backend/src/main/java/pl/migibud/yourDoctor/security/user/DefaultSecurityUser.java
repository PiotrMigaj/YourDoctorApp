package pl.migibud.yourDoctor.security.user;

import lombok.*;

import java.util.List;

@Getter(value = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class DefaultSecurityUser {
    private String username;
    private String password;
    private List<String> roles;
}
