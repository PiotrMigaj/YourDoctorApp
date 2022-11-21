package pl.migibud.yourDoctor.medical.specialization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.migibud.yourDoctor.doctor.Doctor;
import pl.migibud.yourDoctor.doctor.dto.DoctorDto;
import pl.migibud.yourDoctor.medical.specialization.MedicalSpecialization;
import pl.migibud.yourDoctor.medical.specialization.dto.SpecializationDto;

@Mapper(componentModel = "spring", imports = {java.util.stream.Collectors.class})
public interface SpecializationMapper {
    @Mappings(value = {
            @Mapping(source = "specialization", target = "specialization"),
    })
    SpecializationDto mapMedicalSpecializationToSpecializationDto(MedicalSpecialization medicalSpecialization);
}
