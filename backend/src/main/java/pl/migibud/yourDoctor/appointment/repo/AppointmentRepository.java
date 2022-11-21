package pl.migibud.yourDoctor.appointment.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import pl.migibud.yourDoctor.appointment.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);

    Optional<Appointment> findById(Long id);

    Page<Appointment> findAll(Pageable pageable);

    @Query("select a from Appointment a " +
            "where a.doctor.id = ?1 " +
            "and a.dateOfAppointment >= ?2 " +
            "order by a.dateOfAppointment, a.timeOfAppointment")
    Page<Appointment> findByDoctor_IdWithValidDateOfAppointment(Long id, LocalDate dateOfAppointment, Pageable pageable);

    @Query("select a from Appointment a " +
            "where a.appUser.id = ?1 " +
            "and a.dateOfAppointment >= ?2 " +
            "order by a.dateOfAppointment, a.timeOfAppointment")
    Page<Appointment> findByAppUserId_IdWithValidDateOfAppointment(Long id, LocalDate dateOfAppointment, Pageable pageable);

    @Query("select a from Appointment a " +
            "where a.doctor.id = ?1 " +
            "and a.dateOfAppointment > ?2 " +
            "and a.taken = false " +
            "order by a.dateOfAppointment, a.timeOfAppointment")
    Page<Appointment> findByDoctor_IdWithValidDateTimeOfAppointmentAndNotTaken(Long id, LocalDate dateOfAppointment, LocalTime timeOfAppointment, Pageable pageable);

    Optional<Appointment> findByDateOfAppointmentAndTimeOfAppointment(LocalDate dateOfAppointment, LocalTime timeOfAppointment);
}
