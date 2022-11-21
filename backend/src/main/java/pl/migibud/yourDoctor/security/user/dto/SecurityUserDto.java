package pl.migibud.yourDoctor.security.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityUserDto {
    private Long id;
    private String email;
    private List<String> roles;
}
