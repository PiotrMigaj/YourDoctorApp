package pl.migibud.yourDoctor.doctor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.migibud.yourDoctor.doctor.Doctor;
import pl.migibud.yourDoctor.doctor.dto.DoctorView;

import java.util.List;

interface SqlDoctorRepository extends DoctorRepository, JpaRepository<Doctor, Long> {
    @Override
    @Query("select distinct d from Doctor d join fetch d.specializations join fetch d.addresses")
    List<DoctorView> findAllBy();
}
