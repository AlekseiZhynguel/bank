package org.example.accounts.domain.events;

import org.example.domain.DomainEvent;

import java.util.Objects;

public class AccountCreated extends DomainEvent {

  public AccountCreated(String aggregateId) {
    super(aggregateId);
  }

  @Override
  public String eventName() {
    return "account.created";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AccountCreated that)) return false;
    return Objects.equals(aggregateId(), that.aggregateId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(aggregateId());
  }
}
