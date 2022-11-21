package pl.migibud.yourDoctor.medical.specialization;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/specialization")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class SpecializationController {

    private final SpecializationService specializationService;

    @GetMapping
    ResponseEntity<List<MedicalSpecialization.Specialization>> getAllSpecializations(){
        return ResponseEntity.ok(specializationService.getAllSpecializations());
    }
}
