package org.example.infrastructure.events.noop;

import java.util.List;
import org.example.domain.DomainEvent;
import org.example.domain.EventBus;
import org.springframework.stereotype.Component;

@Component
public class EventBusNoop implements EventBus {
  @Override
  public void publish(List<DomainEvent> events) {}
}
