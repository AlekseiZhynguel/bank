package org.example.payments.transactions.domain.events;

import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.AccountIdMother;
import org.example.payments.transactions.domain.TransactionAmount;
import org.example.payments.transactions.domain.TransactionAmountMother;
import org.example.payments.transactions.domain.TransactionId;
import org.example.payments.transactions.domain.TransactionIdMother;

public class TransactionCreatedMother {

  public static TransactionCreated create(
      TransactionId id, AccountId origin, AccountId destination, TransactionAmount amount) {
    return new TransactionCreated(id.value(), origin.value(), destination.value(), amount.value());
  }

  public static TransactionCreated random() {
    return create(
        TransactionIdMother.random(),
        AccountIdMother.random(),
        AccountIdMother.random(),
        TransactionAmountMother.random());
  }
}
