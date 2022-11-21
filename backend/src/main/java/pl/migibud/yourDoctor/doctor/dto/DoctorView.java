package pl.migibud.yourDoctor.doctor.dto;

import pl.migibud.yourDoctor.address.dto.AddressView;
import pl.migibud.yourDoctor.medical.specialization.dto.SpecializationView;

import java.util.List;

public interface DoctorView {
    Long getId();

    String getPhoneNumber();
    List<SpecializationView> getSpecializations();
    List<AddressView> getAddresses();
    List<AppUserView> getAppUser();

    interface AppUserView{
        String getFirstName();
        String getLastName();

    }

}
