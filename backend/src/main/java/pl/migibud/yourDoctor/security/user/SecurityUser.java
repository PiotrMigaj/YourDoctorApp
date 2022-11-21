package pl.migibud.yourDoctor.security.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.migibud.yourDoctor.appointment.Appointment;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "security_user")
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder(access = AccessLevel.PACKAGE)
public class SecurityUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String email;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "security_user_security_user_role",
            joinColumns = @JoinColumn(name = "security_user_id"),
            inverseJoinColumns = @JoinColumn(name = "security_user_role_id")
    )
    Set<SecurityUserRole> roles;

    SecurityUser(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
