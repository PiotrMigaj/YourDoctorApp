package pl.migibud.yourDoctor.user.repo;

import pl.migibud.yourDoctor.user.AppUser;

import java.util.Optional;

public interface AppUserRepository {
    Optional<AppUser> findById(Long id);
    AppUser save(AppUser appUser);
}
