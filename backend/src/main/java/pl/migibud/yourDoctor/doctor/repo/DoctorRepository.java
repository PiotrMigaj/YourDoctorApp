package pl.migibud.yourDoctor.doctor.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import pl.migibud.yourDoctor.doctor.Doctor;
import pl.migibud.yourDoctor.doctor.dto.DoctorView;
import pl.migibud.yourDoctor.medical.specialization.MedicalSpecialization;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository {
    Optional<Doctor> findById(Long id);
    List<DoctorView> findAllBy();
    Page<Doctor> findByAddresses_CityContainingIgnoreCase(String city,Pageable pageable);
    long count();
    Page<Doctor> findAll(Pageable pageable);
    Doctor save(Doctor doctor);
    <S extends Doctor> List<S> saveAll(Iterable<S> entities);
    Page<Doctor> findBySpecializations_SpecializationAndAddresses_CityContainingIgnoreCase(MedicalSpecialization.Specialization specialization, String city, Pageable pageable);

    Page<Doctor> findBySpecializations_Specialization(MedicalSpecialization.Specialization specialization, Pageable pageable);


    List<Doctor> findTop3ByOrderByCreatedAtDesc();
}
