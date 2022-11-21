package pl.migibud.yourDoctor.doctor;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.migibud.yourDoctor.doctor.dto.CreateDoctorRequest;
import pl.migibud.yourDoctor.doctor.dto.DoctorDto;
import pl.migibud.yourDoctor.doctor.dto.DoctorView;
import pl.migibud.yourDoctor.doctor.repo.DoctorRepository;
import pl.migibud.yourDoctor.medical.specialization.MedicalSpecialization;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static pl.migibud.yourDoctor.medical.specialization.MedicalSpecialization.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class DoctorController {

    private final DoctorService doctorService;
    private final DoctorRepository doctorRepository;


    @GetMapping("/{id}")
    ResponseEntity<DoctorDto> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @GetMapping
    ResponseEntity<Page<DoctorDto>> getAllDoctors(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String specialization
    ) {
        log.info("Get all doctors with city param: {}", city);
        log.info("Get all doctors with specialization param: {}", specialization);
        return ResponseEntity.ok(doctorService.getAllDoctors(PageRequest.of(page, size),city,specialization));
    }

    @GetMapping("/recently-joined")
    ResponseEntity<List<DoctorDto>> getRecentlyJoinedDoctors(){
        return ResponseEntity.ok(doctorService.getRecentlyJoinedDoctors());
    }

    @GetMapping("/projection")
    ResponseEntity<List<DoctorView>> getAllDoctorsAsView() {
        return ResponseEntity.ok(doctorRepository.findAllBy());
    }

    @PostMapping
    ResponseEntity<DoctorDto> registerDoctor(@RequestBody @Valid CreateDoctorRequest createDoctorRequest) {
        DoctorDto doctorDto = doctorService.registerDoctor(createDoctorRequest);
        return ResponseEntity.created(URI.create("/api/doctor/" + doctorDto.getId())).body(doctorDto);
    }
}
