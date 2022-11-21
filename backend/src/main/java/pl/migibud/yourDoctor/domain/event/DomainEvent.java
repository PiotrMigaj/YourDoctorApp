package pl.migibud.yourDoctor.domain.event;

import java.time.Instant;

public interface DomainEvent {
    Instant getOccurredOn();
}
