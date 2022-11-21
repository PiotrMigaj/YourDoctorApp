package pl.migibud.yourDoctor.medical.specialization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.migibud.yourDoctor.exception.medical.specialization.SpecializationError;
import pl.migibud.yourDoctor.exception.medical.specialization.SpecializationException;
import pl.migibud.yourDoctor.medical.specialization.dto.CreateSpecializationRequest;
import pl.migibud.yourDoctor.medical.specialization.repo.SpecializationRepository;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
class SpecializationServiceImpl implements SpecializationService {

    private final SpecializationRepository specializationRepository;

    @Override
    public List<MedicalSpecialization.Specialization> getAllSpecializations() {
        return Arrays.stream(MedicalSpecialization.Specialization.values())
                .sorted(Comparator.comparing(Enum::toString))
                .collect(Collectors.toList());
    }

    @Override
    public MedicalSpecialization createMedicalSpecialization(CreateSpecializationRequest createSpecializationRequest) {
        return specializationRepository.findBySpecialization(createSpecializationRequest.getSpecialization())
                .orElseThrow(() -> new SpecializationException(SpecializationError.MEDICAL_SPECIALIZATION_NOT_FOUND));
    }
}
