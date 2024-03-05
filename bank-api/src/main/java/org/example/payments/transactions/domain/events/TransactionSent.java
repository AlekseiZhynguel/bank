package org.example.payments.transactions.domain.events;

import org.example.domain.DomainEvent;

import java.util.Objects;

public class TransactionSent extends DomainEvent {

  private final String originAccountId;
  private final String destinationAccountId;
  private final Integer amount;

  public TransactionSent(
      String aggregateId, String originAccountId, String destinationAccountId, Integer amount) {
    super(aggregateId);
    this.originAccountId = originAccountId;
    this.destinationAccountId = destinationAccountId;
    this.amount = amount;
  }

  @Override
  public String eventName() {
    return null;
  }

  public String originAccountId() {
    return originAccountId;
  }

  public String destinationAccountId() {
    return destinationAccountId;
  }

  public Integer amount() {
    return amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TransactionSent that = (TransactionSent) o;
    return Objects.equals(originAccountId, that.originAccountId)
        && Objects.equals(destinationAccountId, that.destinationAccountId)
        && Objects.equals(amount, that.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(originAccountId, destinationAccountId, amount);
  }
}
