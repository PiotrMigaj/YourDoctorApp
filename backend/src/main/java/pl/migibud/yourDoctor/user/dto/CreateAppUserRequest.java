package pl.migibud.yourDoctor.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NotNull
public class CreateAppUserRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
