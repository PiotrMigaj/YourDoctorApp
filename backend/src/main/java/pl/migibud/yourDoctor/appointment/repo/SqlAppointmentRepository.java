package pl.migibud.yourDoctor.appointment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.migibud.yourDoctor.appointment.Appointment;

interface SqlAppointmentRepository extends AppointmentRepository,JpaRepository<Appointment,Long> {
}
