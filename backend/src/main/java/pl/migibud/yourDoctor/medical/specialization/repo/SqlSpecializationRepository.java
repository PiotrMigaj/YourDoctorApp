package pl.migibud.yourDoctor.medical.specialization.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.migibud.yourDoctor.medical.specialization.MedicalSpecialization;

interface SqlSpecializationRepository extends SpecializationRepository, JpaRepository<MedicalSpecialization,Long> {
}
