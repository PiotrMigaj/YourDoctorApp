package pl.migibud.yourDoctor.medical.specialization.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import static pl.migibud.yourDoctor.medical.specialization.MedicalSpecialization.Specialization;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NotNull
public class CreateSpecializationRequest {
    private Specialization specialization;
}
