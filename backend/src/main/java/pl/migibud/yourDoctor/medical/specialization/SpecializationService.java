package pl.migibud.yourDoctor.medical.specialization;

import pl.migibud.yourDoctor.medical.specialization.dto.CreateSpecializationRequest;
import pl.migibud.yourDoctor.medical.specialization.dto.SpecializationDto;

import java.util.List;

public interface SpecializationService {
    MedicalSpecialization createMedicalSpecialization(CreateSpecializationRequest createSpecializationRequest);
    List<MedicalSpecialization.Specialization> getAllSpecializations();
}
