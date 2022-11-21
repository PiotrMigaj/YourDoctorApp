package pl.migibud.yourDoctor.security.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Getter(value = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@ToString
@Configuration
@ConfigurationProperties(prefix = "application.default")
class InitialSecurityUsersConfiguration {
    private List<String> roles;
    private List<DefaultSecurityUser> users;
}
