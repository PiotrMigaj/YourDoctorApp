package pl.migibud.yourDoctor.appointment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pl.migibud.yourDoctor.YourDoctorApplication;
import pl.migibud.yourDoctor.address.Address;
import pl.migibud.yourDoctor.address.repo.AddressRepository;
import pl.migibud.yourDoctor.appointment.dto.CreateAppointmentRequest;
import pl.migibud.yourDoctor.doctor.Doctor;
import pl.migibud.yourDoctor.doctor.repo.DoctorRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = YourDoctorApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
class AppointmentIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    AddressRepository addressRepository;

    @Test
    void given_when_then(){
        //given
        Address address = new Address("Wrocław","51-141","Długa","14");
        addressRepository.save(address);
        System.out.println(address.getId());

//        Doctor doctor = new Doctor();
//        doctor.
//
//        CreateAppointmentRequest request = new CreateAppointmentRequest()
        //when

        //then
    }




}