package pl.migibud.yourDoctor.doctor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.migibud.yourDoctor.address.dto.CreateAddressRequest;
import pl.migibud.yourDoctor.medical.specialization.dto.CreateSpecializationRequest;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NotNull
public class CreateDoctorRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private CreateSpecializationRequest createSpecializationRequest;
    private CreateAddressRequest createAddressRequest;
}
