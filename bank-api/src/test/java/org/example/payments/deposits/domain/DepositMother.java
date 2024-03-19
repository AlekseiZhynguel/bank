package org.example.payments.deposits.domain;

import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.AccountIdMother;
import org.example.payments.deposits.domain.primitives.DepositPrimitives;
import org.example.payments.deposits.infrastructure.controller.dto.CreateDepositRequest;

public class DepositMother {

  public static Deposit create(
      DepositId id, AccountId destinationId, DepositAmount amount, DepositDescription description) {
    return Deposit.from(
        new DepositPrimitives(
            id.value(), destinationId.value(), amount.value(), description.value()));
  }

  public static Deposit random() {
    return create(
        DepositIdMother.random(),
        AccountIdMother.random(),
        DepositAmountMother.random(),
        DepositDescriptionMother.random());
  }

  public static Deposit fromRequest(DepositId id, CreateDepositRequest request) {
    return Deposit.from(
        new DepositPrimitives(
            id.value(), request.destinationAccount(), request.amount(), request.description()));
  }
}
