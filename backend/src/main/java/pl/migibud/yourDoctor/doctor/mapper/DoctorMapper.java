package pl.migibud.yourDoctor.doctor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.migibud.yourDoctor.doctor.Doctor;
import pl.migibud.yourDoctor.doctor.dto.DoctorDto;
import pl.migibud.yourDoctor.doctor.dto.DoctorForUserDto;

@Mapper(componentModel = "spring", imports = {java.util.stream.Collectors.class})
public interface DoctorMapper {
    @Mappings(value = {
            @Mapping(source = "id", target = "id"),
            @Mapping(expression = "java(doctor.getAppUser().getFirstName())", target = "firstName"),
            @Mapping(expression = "java(doctor.getAppUser().getLastName())", target = "lastName"),
            @Mapping(expression = "java(doctor.getAppUser().getSecurityUser().getEmail())", target = "email"),
            @Mapping(source = "phoneNumber", target = "phoneNumber"),
            @Mapping(expression = "java(doctor.getSpecializations().stream().map(s->s.getSpecialization().name()).collect(Collectors.toList()))", target = "specializations"),
            @Mapping(source = "addresses", target = "addresses"),
    })
    DoctorDto mapDoctorToDoctorDto(Doctor doctor);

    @Mappings(value = {
            @Mapping(source = "id", target = "id"),
            @Mapping(expression = "java(doctor.getAppUser().getFirstName())", target = "firstName"),
            @Mapping(expression = "java(doctor.getAppUser().getLastName())", target = "lastName"),
            @Mapping(expression = "java(doctor.getAppUser().getSecurityUser().getEmail())", target = "email"),
            @Mapping(source = "phoneNumber", target = "phoneNumber"),
            @Mapping(expression = "java(doctor.getSpecializations().stream().map(s->s.getSpecialization().name()).collect(Collectors.toList()))", target = "specializations"),
    })
    DoctorForUserDto mapDoctorToDoctorForUserDto(Doctor doctor);
}
