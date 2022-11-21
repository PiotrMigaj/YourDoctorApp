package pl.migibud.yourDoctor.user;

import pl.migibud.yourDoctor.user.dto.AppUserDto;
import pl.migibud.yourDoctor.user.dto.CreateAppUserRequest;

public interface AppUserService {
    AppUserDto registerAppUser(CreateAppUserRequest createAppUserRequest);
    AppUserDto getUserById(Long userId);
}
