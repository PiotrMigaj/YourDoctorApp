package pl.migibud.yourDoctor.security.user;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface SecurityUserService {
    SecurityUser createSecurityUser(final String email, final String password);
    SecurityUser createSecurityUserForDoctor(final String email, final String password);
    SecurityUser addRoleToSecurityUser(final Long securityUserId, final String roleName);
    SecurityUser getUser(UsernamePasswordAuthenticationToken springToken,Long userId);
}
