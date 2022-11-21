package pl.migibud.yourDoctor.doctor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.migibud.yourDoctor.address.dto.AddressDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorForUserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    List<String> specializations;
}
