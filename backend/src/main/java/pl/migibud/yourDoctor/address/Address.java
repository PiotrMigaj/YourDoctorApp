package pl.migibud.yourDoctor.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.migibud.yourDoctor.doctor.Doctor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "address")
@Getter
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String zipCode;
    private String street;
    private String number;

    @JsonIgnore
    @ManyToMany(mappedBy = "addresses")
    @ToString.Exclude
    private Set<Doctor> doctor;

    public Address(String city, String zipCode, String street, String number) {
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.number = number;
    }
}
