package pl.migibud.yourDoctor.appointment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.migibud.yourDoctor.address.Address;
import pl.migibud.yourDoctor.doctor.Doctor;
import pl.migibud.yourDoctor.user.AppUser;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "appointment")
@Getter
@Setter(value = AccessLevel.PACKAGE)
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateOfAppointment;
    private LocalTime timeOfAppointment;
    private LocalDateTime registrationTime;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private boolean taken;
    @OneToOne
    @JoinColumn(name = "address_id")
    @JsonIgnore
    private Address address;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonIgnore
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private AppUser appUser;

    Appointment(LocalDate dateOfAppointment, LocalTime timeOfAppointment, Address address, Doctor doctor) {
        this.dateOfAppointment = dateOfAppointment;
        this.timeOfAppointment = timeOfAppointment;
        this.address = address;
        this.doctor = doctor;
    }
}
