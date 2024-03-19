package org.example.accounts.domain.events;

import org.example.payments.transactions.domain.events.TransactionCreated;

public class AccountsVerifiedOnTransactionSentMother {
  public static AccountsVerifiedOnTransactionSent fromTransactionSent(TransactionCreated event) {
    return new AccountsVerifiedOnTransactionSent(
        event.aggregateId(), event.originAccountId(), event.destinationAccountId(), event.amount());
  }
}
