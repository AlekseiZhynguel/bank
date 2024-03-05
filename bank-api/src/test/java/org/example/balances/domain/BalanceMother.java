package org.example.balances.domain;

import org.example.accounts.domain.*;
import org.example.balances.domain.primitives.BalancePrimitives;

public class BalanceMother {

  public static Balance create(BalanceId id, AccountId accountId, BalanceAmount amount) {
    return Balance.from(new BalancePrimitives(id.value(), accountId.value(), amount.value()));
  }

  public static Balance random() {
    return create(BalanceIdMother.random(), AccountIdMother.random(), BalanceAmountMother.random());
  }
}
