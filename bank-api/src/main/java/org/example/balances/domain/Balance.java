package org.example.balances.domain;

import org.example.accounts.domain.AccountId;
import org.example.balances.domain.primitives.BalancePrimitives;

public class Balance {
  private final BalanceId id;
  private final AccountId accountId;
  private BalanceAmount amount;

  private Balance(String id, String accountId, Integer amount) {
    this.id = new BalanceId(id);
    this.accountId = new AccountId(accountId);
    this.amount = new BalanceAmount(amount);
  }

  public static Balance zeroFor(String id, String accountId) {
    return new Balance(id, accountId, BalanceAmount.zero().value());
  }

  public static Balance from(BalancePrimitives primitives) {
    return new Balance(primitives.id(), primitives.accountId(), primitives.amount());
  }

  public BalancePrimitives toPrimitives() {
    return new BalancePrimitives(id.value(), accountId.value(), amount.value());
  }

  public BalanceId id() {
    return id;
  }

  public AccountId accountId() {
    return accountId;
  }

  public BalanceAmount amount() {
    return amount;
  }

  public void increaseIn(Integer other) {
    this.amount = amount.add(other);
  }

  public void decreaseIn(Integer other) {
    this.amount = amount.subtract(other);
  }
}
