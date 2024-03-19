package org.example.payments.transactions.domain;

import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.AccountIdMother;
import org.example.payments.transactions.infrastructure.controller.dto.CreateTransactionRequest;

public class TransactionMother {

  public static Transaction create(
      TransactionId id,
      AccountId origin,
      AccountId destination,
      TransactionAmount amount,
      TransactionDescription description) {
    return new Transaction(
        id.value(), origin.value(), destination.value(), amount.value(), description.value());
  }

  public static Transaction random() {
    return create(
        TransactionIdMother.random(),
        AccountIdMother.random(),
        AccountIdMother.random(),
        TransactionAmountMother.random(),
        TransactionDescriptionMother.random());
  }

  public static Transaction fromRequest(TransactionId id, CreateTransactionRequest request) {
    return new Transaction(
        id.value(),
        request.originAccount(),
        request.destinationAccount(),
        request.amount(),
        request.description());
  }
}
