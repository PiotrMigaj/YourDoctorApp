package pl.migibud.yourDoctor.doctor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.migibud.yourDoctor.address.dto.AddressDto;
import pl.migibud.yourDoctor.user.dto.AppUserDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    List<String> specializations;
    List<AddressDto> addresses;
}
