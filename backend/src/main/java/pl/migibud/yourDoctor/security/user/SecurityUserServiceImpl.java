package pl.migibud.yourDoctor.security.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.migibud.yourDoctor.exception.security.user.SecurityUserError;
import pl.migibud.yourDoctor.exception.security.user.SecurityUserException;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.HashSet;

@Slf4j
@Service
@RequiredArgsConstructor
class SecurityUserServiceImpl implements SecurityUserService {

    @Value("${application.default.newUserRole:ROLE_USER}")
    private String defaultNewUserRole;
    @Value("${application.default.newDoctorRole:ROLE_DOCTOR}")
    private String defaultNewDoctorRole;
    private final PasswordEncoder passwordEncoder;
    private final SecurityUserRepository securityUserRepository;
    private final SecurityUserRoleRepository securityUserRoleRepository;

    @Override
    @Transactional
    public SecurityUser createSecurityUser(String email, String password) {

        if (securityUserRepository.existsByEmail(email)){
            throw new SecurityUserException(SecurityUserError.SECURITY_USER_ALREADY_EXISTS);
        }

        SecurityUser securityUser = new SecurityUser(email, passwordEncoder.encode(password));
        securityUser.setAccountNonExpired(true);
        securityUser.setAccountNonLocked(true);
        securityUser.setEnabled(true);
        securityUser.setCredentialsNonExpired(true);
        securityUser.setRoles(
                new HashSet<>(
                        Collections.singletonList(securityUserRoleRepository
                                .findByName(defaultNewUserRole)
                                .orElseThrow(EntityNotFoundException::new))));
        return securityUserRepository.save(securityUser);
    }

    @Override
    @Transactional
    public SecurityUser createSecurityUserForDoctor(String email, String password) {
        SecurityUser securityUser = this.createSecurityUser(email, password);
        SecurityUserRole securityUserRole = securityUserRoleRepository
                .findByName(defaultNewDoctorRole)
                .orElseThrow(EntityNotFoundException::new);
        securityUser.getRoles().add(securityUserRole);
        return securityUserRepository.save(securityUser);
    }

    @Override
    @Transactional
    public SecurityUser addRoleToSecurityUser(Long securityUserId, String roleName) {

        return securityUserRepository.findById(securityUserId).
                map(securityUser -> {
                    SecurityUserRole securityUserRole = securityUserRoleRepository
                            .findByName(roleName)
                            .orElseThrow(EntityNotFoundException::new);
                    securityUser.getRoles().add(securityUserRole);
                    return securityUserRepository.save(securityUser);
                }).orElseThrow(() -> new SecurityUserException(SecurityUserError.SECURITY_USER_NOT_FOUND));
    }

    @Override
    public SecurityUser getUser(UsernamePasswordAuthenticationToken springToken,Long userId){
        return securityUserRepository.findByEmail(String.valueOf(springToken.getPrincipal()))
                .map(securityUser -> {
                    if (!securityUser.getId().equals(userId)){
                        throw new SecurityUserException(SecurityUserError.SECURITY_USER_ID_DOES_NOT_MATCH);
                    }
                    return securityUser;
                })
                .orElseThrow(()->new SecurityUserException(SecurityUserError.SECURITY_USER_NOT_FOUND));
    }
}
