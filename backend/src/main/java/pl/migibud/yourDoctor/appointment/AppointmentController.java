package pl.migibud.yourDoctor.appointment;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import pl.migibud.yourDoctor.appointment.dto.*;
import pl.migibud.yourDoctor.security.user.SecurityUserService;

import javax.validation.Valid;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AppointmentController {

    private final AppointmentService appointmentService;
    private final SecurityUserService securityUserService;

    @GetMapping
    @Secured(value = {"ROLE_ADMIN"})
    ResponseEntity<Page<AppointmentDto>> getAllAppointments(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ){
        return ResponseEntity.ok(appointmentService.getAllAppointments(PageRequest.of(page,size)));
    }

    @GetMapping("/doctor/{doctorId}")
    @Secured(value = {"ROLE_DOCTOR"})
    ResponseEntity<Page<AppointmentForDoctorDto>> getAllAppointmentsForDoctorWithDoctorId(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @PathVariable Long doctorId,
            UsernamePasswordAuthenticationToken principal
    ){
        securityUserService.getUser(principal,doctorId);
        return ResponseEntity.ok(appointmentService.getAllAppointmentsForDoctorWithId(PageRequest.of(page,size),doctorId));
    }

    @GetMapping("/user/{userId}")
    @Secured(value = {"ROLE_USER"})
    ResponseEntity<Page<AppointmentForUserDto>> getAllAppointmentsForAppUserWithAppUserId(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @PathVariable Long userId,
            UsernamePasswordAuthenticationToken principal
    ){
        securityUserService.getUser(principal,userId);
        return ResponseEntity.ok(appointmentService.getAllAppointmentsForAppUserWithId(PageRequest.of(page,size),userId));
    }

    @GetMapping("/doctor/{doctorId}/make-appointment")
    ResponseEntity<Page<AppointmentForUserDto>> getAllAvailableAppointmentsForDoctorWithId(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @PathVariable Long doctorId
    ){
        return ResponseEntity.ok(appointmentService.getAllAvailableAppointmentsForDoctorWithId(PageRequest.of(page,size),doctorId));
    }


    @PostMapping
    @Secured(value = {"ROLE_DOCTOR"})
    ResponseEntity<AppointmentDto> createAppointment(
            @RequestBody @Valid CreateAppointmentRequest createAppointmentRequest,
            UsernamePasswordAuthenticationToken principal
    ){
        securityUserService.getUser(principal,createAppointmentRequest.getDoctorId());
        log.info("Got request with createAppointmentRequest: {}",createAppointmentRequest);
        return ResponseEntity.ok(appointmentService.addAppointment(createAppointmentRequest));
    }

    @PatchMapping("/make-appointment")
    @Secured(value = {"ROLE_USER"})
    ResponseEntity<AppointmentDto> registerUserToAppointment(
            @RequestBody @Valid CreateAppointmentRegistrationRequest request,
            UsernamePasswordAuthenticationToken principal
    ){
        securityUserService.getUser(principal,request.getUserId());
        return ResponseEntity.ok(appointmentService.registerUserToAppointment(request));
    }


}
