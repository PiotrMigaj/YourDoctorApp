package pl.migibud.yourDoctor.user;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.migibud.yourDoctor.domain.event.DomainEventPublisher;
import pl.migibud.yourDoctor.exception.user.AppUserError;
import pl.migibud.yourDoctor.exception.user.AppUserException;
import pl.migibud.yourDoctor.security.user.SecurityUser;
import pl.migibud.yourDoctor.security.user.SecurityUserService;
import pl.migibud.yourDoctor.user.dto.AppUserDto;
import pl.migibud.yourDoctor.user.dto.CreateAppUserRequest;
import pl.migibud.yourDoctor.user.mapper.AppUserMapper;
import pl.migibud.yourDoctor.user.repo.AppUserRepository;
import pl.migibud.yourDoctor.user.vo.AppUserEvent;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final SecurityUserService securityUserService;
    private final AppUserMapper appUserMapper;
    private final DomainEventPublisher domainEventPublisher;


    @Override
    @Transactional
    public AppUserDto registerAppUser(CreateAppUserRequest createAppUserRequest) {

        SecurityUser securityUser = securityUserService.createSecurityUser(
                createAppUserRequest.getEmail(),
                createAppUserRequest.getPassword()
        );

        AppUser appUser = new AppUser(
                securityUser.getId(),
                createAppUserRequest.getFirstName(),
                createAppUserRequest.getLastName(),
                securityUser
        );
        appUserRepository.save(appUser);
        publishRegistrationEvent(securityUser, appUser);
        return appUserMapper.mapAppUserToAppUserDto(appUser);
    }

    private void publishRegistrationEvent(SecurityUser securityUser, AppUser appUser) {
        domainEventPublisher.publish(new AppUserEvent(new AppUserEvent.Data(
                appUser.getFirstName(),
                appUser.getLastName(),
                securityUser.getEmail()
        )));
    }

    @Override
    public AppUserDto getUserById(Long userId) {
        return appUserRepository.findById(userId)
                .map(appUserMapper::mapAppUserToAppUserDto)
                .orElseThrow(()->new AppUserException(AppUserError.USER_NOT_FOUND));
    }
}
