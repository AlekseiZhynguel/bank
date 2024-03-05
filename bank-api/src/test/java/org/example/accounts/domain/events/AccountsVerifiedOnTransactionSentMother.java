package org.example.accounts.domain.events;

import org.example.payments.transactions.domain.events.TransactionSent;

public class AccountsVerifiedOnTransactionSentMother {
  public static AccountsVerifiedOnTransactionSent fromTransactionSent(TransactionSent event) {
    return new AccountsVerifiedOnTransactionSent(
        event.aggregateId(), event.originAccountId(), event.destinationAccountId(), event.amount());
  }
}
