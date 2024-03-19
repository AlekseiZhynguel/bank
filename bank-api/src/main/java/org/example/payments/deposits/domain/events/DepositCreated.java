package org.example.payments.deposits.domain.events;

import java.util.Objects;
import org.example.domain.DomainEvent;

public class DepositCreated extends DomainEvent {

  private final String accountId;
  private final Integer amount;

  public DepositCreated(String aggregateId, String accountId, Integer amount) {
    super(aggregateId);
    this.accountId = accountId;
    this.amount = amount;
  }

  @Override
  public String eventName() {
    return "payment.deposit.created";
  }

  public String accountId() {
    return accountId;
  }

  public Integer amount() {
    return amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DepositCreated that = (DepositCreated) o;
    return Objects.equals(accountId, that.accountId) && Objects.equals(amount, that.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId, amount);
  }
}
