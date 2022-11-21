package pl.migibud.yourDoctor.appointment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.migibud.yourDoctor.appointment.Appointment;
import pl.migibud.yourDoctor.appointment.dto.AppointmentDto;
import pl.migibud.yourDoctor.appointment.dto.AppointmentForDoctorDto;
import pl.migibud.yourDoctor.appointment.dto.AppointmentForUserDto;
import pl.migibud.yourDoctor.doctor.dto.DoctorForUserDto;
import pl.migibud.yourDoctor.doctor.mapper.DoctorMapper;
import pl.migibud.yourDoctor.user.mapper.AppUserMapper;

@Mapper(componentModel = "spring",uses = {DoctorMapper.class, AppUserMapper.class})
public interface AppointmentMapper {
    @Mappings(value = {
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "dateOfAppointment", target = "dateOfAppointment"),
            @Mapping(source = "timeOfAppointment", target = "timeOfAppointment"),
            @Mapping(source = "taken", target = "taken"),
    })
    AppointmentDto mapAppointmentToAppointmentDto(Appointment appointment);

    @Mappings(value = {
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "dateOfAppointment", target = "dateOfAppointment"),
            @Mapping(source = "timeOfAppointment", target = "timeOfAppointment"),
            @Mapping(source = "doctor", target = "doctor"),
            @Mapping(source = "address", target = "address"),
            @Mapping(source = "taken", target = "taken"),
    })
    AppointmentForUserDto mapAppointmentToAppointmentForUserDto(Appointment appointment);

    @Mappings(value = {
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "dateOfAppointment", target = "dateOfAppointment"),
            @Mapping(source = "timeOfAppointment", target = "timeOfAppointment"),
            @Mapping(source = "appUser", target = "user"),
            @Mapping(source = "address", target = "address"),
            @Mapping(source = "taken", target = "taken"),
    })
    AppointmentForDoctorDto mapAppointmentToAppointmentForDoctorDto(Appointment appointment);
}
