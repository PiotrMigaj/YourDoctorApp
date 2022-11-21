package pl.migibud.yourDoctor.security.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
class SecurityUserWarmup {

    private final InitialSecurityUsersConfiguration initialSecurityUsersConfiguration;
    private final SecurityUserRepository securityUserRepository;
    private final SecurityUserRoleRepository securityUserRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ContextRefreshedEvent.class)
    @Order(1)
    void initializeDatabase() {
        log.info(String.format("Initial users: %s", initialSecurityUsersConfiguration));
        initializeRoles(initialSecurityUsersConfiguration.getRoles());
        initializeSecurityUsers(initialSecurityUsersConfiguration.getUsers());
    }

    private void initializeSecurityUsers(List<DefaultSecurityUser> users) {
        users.stream()
                .filter(defaultSecurityUser -> !securityUserRepository.existsByEmail(defaultSecurityUser.getUsername()))
                .map(this::mapDefaultSecurityUserToSecurityUser)
                .forEach(securityUserRepository::save);
    }

    private SecurityUser mapDefaultSecurityUserToSecurityUser(DefaultSecurityUser defaultSecurityUser) {
        Set<SecurityUserRole> securityUserRoles = defaultSecurityUser.getRoles()
                .stream()
                .map(securityUserRoleRepository::findByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        return SecurityUser.builder()
                .password(passwordEncoder.encode(defaultSecurityUser.getPassword()))
                .email(defaultSecurityUser.getUsername())
                .roles(securityUserRoles)
                .credentialsNonExpired(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .enabled(true)
                .build();
    }

    public void initializeRoles(List<String> roleNames) {
        roleNames.stream()
                .filter(name -> !securityUserRoleRepository.existsByName(name))
                .forEach(name -> securityUserRoleRepository.save(new SecurityUserRole(name)));
    }

}
