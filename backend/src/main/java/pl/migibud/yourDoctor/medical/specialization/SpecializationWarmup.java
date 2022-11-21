package pl.migibud.yourDoctor.medical.specialization;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pl.migibud.yourDoctor.medical.specialization.repo.SpecializationRepository;

import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class SpecializationWarmup {
    private final SpecializationRepository specializationRepository;

    @EventListener(ContextRefreshedEvent.class)
    @Order(2)
    void initializeDatabase() {
        if (specializationRepository.count() == 0) {
            MedicalSpecialization.Specialization[] specializations = MedicalSpecialization.Specialization.values();
            Arrays.stream(specializations)
                    .forEach(specialization ->
                            specializationRepository.save(new MedicalSpecialization(specialization))
                    );
        }
    }
}
