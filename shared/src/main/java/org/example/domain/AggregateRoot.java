package org.example.domain;

import java.util.LinkedList;
import java.util.List;

public abstract class AggregateRoot {
  private List<DomainEvent> recordedDomainEvents = new LinkedList<>();

  public final List<DomainEvent> pullDomainEvents() {
    final var recordedDomainEvents = this.recordedDomainEvents;
    this.recordedDomainEvents = new LinkedList<>();

    return recordedDomainEvents;
  }

  protected final void record(DomainEvent event) {
    recordedDomainEvents.add(event);
  }
}
