package pl.migibud.yourDoctor.user.vo;

import lombok.Data;
import pl.migibud.yourDoctor.domain.event.DomainEvent;

import java.time.Instant;

@Data
public class AppUserEvent implements DomainEvent {

    private final Instant occurredOn;
    private final Data data;

    public AppUserEvent(Data data) {
        this.occurredOn = Instant.now();
        this.data = data;
    }

    @Override
    public Instant getOccurredOn() {
        return occurredOn;
    }

    @lombok.Data
    public static class Data{
        private final String firstName;
        private final String lastName;
        private final String email;
    }
}
