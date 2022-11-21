package pl.migibud.yourDoctor.doctor;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pl.migibud.yourDoctor.address.dto.CreateAddressRequest;
import pl.migibud.yourDoctor.doctor.dto.CreateDoctorRequest;
import pl.migibud.yourDoctor.medical.specialization.dto.CreateSpecializationRequest;
import pl.migibud.yourDoctor.doctor.dto.DoctorDto;
import pl.migibud.yourDoctor.doctor.repo.DoctorRepository;

import static pl.migibud.yourDoctor.medical.specialization.MedicalSpecialization.Specialization.*;

@Slf4j
@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class DoctorWarmup {

    private final DoctorService doctorService;
    private final DoctorRepository doctorRepository;

    @EventListener(ContextRefreshedEvent.class)
    @Order(3)
    void initializeDatabase() {
        if (doctorRepository.count() == 0) {
            CreateDoctorRequest doctor1 = CreateDoctorRequest.builder()
                    .email("pmigaj@gmail.com")
                    .password("doc1")
                    .firstName("Piotr")
                    .lastName("Migaj")
                    .phoneNumber("+48669678478")
                    .createSpecializationRequest(new CreateSpecializationRequest(CARDIOLOGIST))
                    .createAddressRequest(new CreateAddressRequest("Wrocław","51-141","Piaseczna","15c"))
                    .build();
            DoctorDto doctorDto1 = doctorService.registerDoctor(doctor1);
            log.info(doctorDto1.toString());

            CreateDoctorRequest doctor2 = CreateDoctorRequest.builder()
                    .email("amigaj@gmail.com")
                    .password("doc2")
                    .firstName("Anna")
                    .lastName("Migaj")
                    .phoneNumber("+48507564123")
                    .createSpecializationRequest(new CreateSpecializationRequest(NEUROLOGIST))
                    .createAddressRequest(new CreateAddressRequest("Poznań","51-145","Długa","18c"))
                    .build();
            DoctorDto doctorDto2 = doctorService.registerDoctor(doctor2);
            log.info(doctorDto2.toString());
        }
    }
}
