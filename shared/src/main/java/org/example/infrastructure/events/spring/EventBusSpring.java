package org.example.infrastructure.events.spring;

import org.example.domain.DomainEvent;
import org.example.domain.EventBus;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventBusSpring implements EventBus {
  private final ApplicationEventPublisher publisher;

  public EventBusSpring(ApplicationEventPublisher publisher) {
    this.publisher = publisher;
  }

  @Override
  public void publish(List<DomainEvent> events) {
    events.forEach(this::publish);
  }

  private void publish(final DomainEvent event) {
    this.publisher.publishEvent(event);
  }
}
