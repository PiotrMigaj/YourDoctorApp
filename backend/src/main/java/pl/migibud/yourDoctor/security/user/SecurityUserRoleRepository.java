package pl.migibud.yourDoctor.security.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface SecurityUserRoleRepository extends JpaRepository<SecurityUserRole,Long> {
    boolean existsByName(String roleName);
    Optional<SecurityUserRole> findByName(String roleName);
}
