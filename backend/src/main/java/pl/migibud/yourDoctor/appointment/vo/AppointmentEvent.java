package pl.migibud.yourDoctor.appointment.vo;

import lombok.Data;
import pl.migibud.yourDoctor.appointment.Appointment;
import pl.migibud.yourDoctor.domain.event.DomainEvent;

import java.time.Instant;

@Data
public class AppointmentEvent implements DomainEvent {

    private final Instant occurredOn;
    private final Data data;

    public AppointmentEvent(Data data) {
        this.occurredOn = Instant.now();
        this.data = data;
    }

    @Override
    public Instant getOccurredOn() {
        return occurredOn;
    }

    @lombok.Data
    public static class Data{
        private final Appointment appointment;
    }
}
