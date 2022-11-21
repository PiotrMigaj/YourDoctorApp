package pl.migibud.yourDoctor.user;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import pl.migibud.yourDoctor.security.user.SecurityUserService;
import pl.migibud.yourDoctor.user.dto.AppUserDto;
import pl.migibud.yourDoctor.user.dto.CreateAppUserRequest;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AppUserController {

    private final AppUserService appUserService;
    private final SecurityUserService securityUserService;

    @GetMapping("/{id}")
    @Secured(value = {"ROLE_USER"})
    ResponseEntity<AppUserDto> getUserById(@PathVariable Long id, UsernamePasswordAuthenticationToken token){
        securityUserService.getUser(token,id);
        return ResponseEntity.ok(appUserService.getUserById(id));
    }

    @PostMapping
    ResponseEntity<AppUserDto> registerUser(@RequestBody @Valid CreateAppUserRequest createAppUserRequest){
        AppUserDto appUserDto = appUserService.registerAppUser(createAppUserRequest);
        return ResponseEntity.created(URI.create("/api/user/"+appUserDto.getId())).body(appUserDto);
    }

}
