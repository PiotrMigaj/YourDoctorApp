package pl.migibud.yourDoctor.medical.specialization.repo;

import pl.migibud.yourDoctor.medical.specialization.MedicalSpecialization;
import pl.migibud.yourDoctor.medical.specialization.dto.SpecializationDto;
import pl.migibud.yourDoctor.medical.specialization.dto.SpecializationView;

import java.util.List;
import java.util.Optional;

public interface SpecializationRepository {
    long count();
    <S extends MedicalSpecialization> S save(S entity);
    Optional<MedicalSpecialization> findBySpecialization(MedicalSpecialization.Specialization specialization);
}
