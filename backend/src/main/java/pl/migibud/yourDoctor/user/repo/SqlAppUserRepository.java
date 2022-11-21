package pl.migibud.yourDoctor.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.migibud.yourDoctor.user.AppUser;

interface SqlAppUserRepository extends AppUserRepository, JpaRepository<AppUser,Long> {
}
