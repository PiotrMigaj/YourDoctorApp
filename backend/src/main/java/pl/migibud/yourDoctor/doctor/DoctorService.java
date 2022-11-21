package pl.migibud.yourDoctor.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pl.migibud.yourDoctor.doctor.dto.CreateDoctorRequest;
import pl.migibud.yourDoctor.doctor.dto.DoctorDto;
import pl.migibud.yourDoctor.medical.specialization.MedicalSpecialization;
import pl.migibud.yourDoctor.user.dto.AppUserDto;
import pl.migibud.yourDoctor.user.dto.CreateAppUserRequest;

import javax.print.Doc;
import java.util.List;

public interface DoctorService {
    DoctorDto registerDoctor(CreateDoctorRequest createDoctorRequest);
    Page<DoctorDto> getAllDoctors(PageRequest pageRequest, String city, String specializationName);
    DoctorDto getDoctorById(Long doctorId);

    List<DoctorDto> getRecentlyJoinedDoctors();
}
