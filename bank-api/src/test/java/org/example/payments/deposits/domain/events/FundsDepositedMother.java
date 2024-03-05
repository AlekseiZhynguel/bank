package org.example.payments.deposits.domain.events;

import org.example.accounts.domain.AccountId;
import org.example.payments.deposits.domain.Deposit;
import org.example.payments.deposits.domain.DepositAmount;
import org.example.payments.deposits.domain.DepositId;
import org.example.payments.deposits.domain.events.FundsDeposited;

public class FundsDepositedMother {
  public static FundsDeposited fromDeposit(Deposit deposit) {
    return create(deposit.id(), deposit.destinationAccount(), deposit.amount());
  }

  public static FundsDeposited create(DepositId id, AccountId destinationId, DepositAmount amount) {
    return new FundsDeposited(id.value(), destinationId.value(), amount.value());
  }
}
