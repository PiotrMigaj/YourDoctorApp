package pl.migibud.yourDoctor.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.migibud.yourDoctor.appointment.Appointment;
import pl.migibud.yourDoctor.doctor.Doctor;
import pl.migibud.yourDoctor.security.user.SecurityUser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder(access = AccessLevel.PACKAGE)
public class AppUser {

    @Id
    @NotNull
    private Long id;
    private String firstName;
    private String lastName;

    @JsonIgnore
    @OneToMany(mappedBy = "appUser")
    private Set<Appointment> appointments;

    @OneToOne
    @JoinColumn(name = "security_user_id")
    private SecurityUser securityUser;

    @OneToOne(mappedBy = "appUser")
    private Doctor doctor;

    public AppUser(Long id, String firstName, String lastName, SecurityUser securityUser) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.securityUser = securityUser;
    }

    public AppUser(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
