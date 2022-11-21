package pl.migibud.yourDoctor.medical.specialization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.migibud.yourDoctor.doctor.Doctor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Set;

@Entity
@Table(name = "specialization")
@Getter
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@ToString
public class MedicalSpecialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Specialization specialization;

    MedicalSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "specializations")
    @ToString.Exclude
    private Set<Doctor> doctor;

    public enum Specialization {
        PEDIATRICIAN,
        DERMATOLOGIST,
        SURGEON,
        PHYSIOTHERAPIST,
        DENTIST,
        ORTHOPAEDIST,
        CARDIOLOGIST,
        NEUROLOGIST,
        OPTICIAN,
        GYNAECOLOGIST,
        SEXOLOGIST,
        OSTEOPATH,
        NEPHROLOGIST,
        PHYSICIAN,
        INTERNIST,
        GERIATRICIAN,
        ONCOLOGIST;

        public static Specialization getSpecialization(String spec) {
             return Arrays.stream(Specialization.values())
                    .filter(s->s.name().equals(spec))
                    .findFirst()
                    .orElse(null);
        }
    }
}

