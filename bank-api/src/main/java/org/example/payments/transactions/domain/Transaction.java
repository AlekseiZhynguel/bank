package org.example.payments.transactions.domain;

import java.util.Objects;
import org.example.accounts.domain.AccountId;
import org.example.domain.AggregateRoot;
import org.example.payments.transactions.domain.events.TransactionSent;
import org.example.payments.transactions.domain.primitives.TransactionPrimitives;

public class Transaction extends AggregateRoot {

  private final TransactionId id;
  private final AccountId originId;
  private final AccountId destinationId;
  private final TransactionAmount amount;
  private final TransactionDescription description;

  public Transaction(
      String id,
      String originAccount,
      String destinationAccount,
      Integer amount,
      String description) {
    this.id = new TransactionId(id);
    this.originId = new AccountId(originAccount);
    this.destinationId = new AccountId(destinationAccount);
    this.amount = new TransactionAmount(amount);
    this.description = new TransactionDescription(description);
  }

  public static Transaction create(
      String id,
      String originAccount,
      String destinationAccount,
      Integer amount,
      String description) {
    Transaction transaction =
        new Transaction(id, originAccount, destinationAccount, amount, description);

    transaction.record(new TransactionSent(id, originAccount, destinationAccount, amount));

    return transaction;
  }

  public static Transaction from(TransactionPrimitives primitives) {
    return new Transaction(
        primitives.id(),
        primitives.originAccount(),
        primitives.destinationAccount(),
        primitives.amount(),
        primitives.description());
  }

  public TransactionPrimitives toPrimitives() {
    return new TransactionPrimitives(
        id.value(), originId.value(), destinationId.value(), amount.value(), description.value());
  }

  public TransactionId id() {
    return id;
  }

  public AccountId originId() {
    return originId;
  }

  public AccountId destinationId() {
    return destinationId;
  }

  public TransactionAmount amount() {
    return amount;
  }

  public TransactionDescription description() {
    return description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Transaction that = (Transaction) o;
    return Objects.equals(id, that.id)
        && Objects.equals(originId, that.originId)
        && Objects.equals(destinationId, that.destinationId)
        && Objects.equals(amount, that.amount)
        && Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, originId, destinationId, amount, description);
  }
}
