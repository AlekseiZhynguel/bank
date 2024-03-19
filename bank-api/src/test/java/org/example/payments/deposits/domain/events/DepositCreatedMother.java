package org.example.payments.deposits.domain.events;

import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.AccountIdMother;
import org.example.payments.deposits.domain.*;

public class DepositCreatedMother {
  public static DepositCreated fromDeposit(Deposit deposit) {
    return create(deposit.id(), deposit.destinationAccount(), deposit.amount());
  }

  public static DepositCreated create(DepositId id, AccountId destinationId, DepositAmount amount) {
    return new DepositCreated(id.value(), destinationId.value(), amount.value());
  }

  public static DepositCreated random() {
    return create(DepositIdMother.random(), AccountIdMother.random(), DepositAmountMother.random());
  }

  public static DepositCreated withAccountIdAndAmount(AccountId accountId, DepositAmount amount) {
    return create(DepositIdMother.random(), accountId, amount);
  }
}
