package pl.migibud.yourDoctor.domain.event;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
