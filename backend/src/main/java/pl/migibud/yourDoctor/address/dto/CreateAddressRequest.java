package pl.migibud.yourDoctor.address.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NotNull
public class CreateAddressRequest {
    private String city;
    private String zipCode;
    private String street;
    private String number;
}
