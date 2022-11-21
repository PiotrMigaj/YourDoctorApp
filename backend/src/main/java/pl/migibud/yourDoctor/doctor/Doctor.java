package pl.migibud.yourDoctor.doctor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.migibud.yourDoctor.address.Address;
import pl.migibud.yourDoctor.appointment.Appointment;
import pl.migibud.yourDoctor.medical.specialization.MedicalSpecialization;
import pl.migibud.yourDoctor.user.AppUser;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "doctor")
@Getter
@Setter(value = AccessLevel.PACKAGE)
@NoArgsConstructor
public class Doctor {

    @Id
    private Long id;

    @Pattern(regexp = "(\\+[0-9]{2})?([0-9]{9})?")
    private String phoneNumber;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(
            name = "doctor_specialization",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id")
    )
    private Set<MedicalSpecialization> specializations;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(
            name = "doctor_address",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<Address> addresses;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointments;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    Doctor(Long id, String phoneNumber, Set<MedicalSpecialization> specializations, Set<Address> addresses, AppUser appUser) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.specializations = specializations;
        this.addresses = addresses;
        this.appUser = appUser;
    }
}
