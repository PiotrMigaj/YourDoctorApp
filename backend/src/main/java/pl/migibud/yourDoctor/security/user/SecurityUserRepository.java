package pl.migibud.yourDoctor.security.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface SecurityUserRepository extends JpaRepository<SecurityUser,Long> {
    boolean existsByEmail(String email);
    Optional<SecurityUser> findByEmail(String email);
}
