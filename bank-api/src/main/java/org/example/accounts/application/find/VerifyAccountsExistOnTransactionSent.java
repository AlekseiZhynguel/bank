package org.example.accounts.application.find;

import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.events.AccountsVerifiedOnTransactionSent;
import org.example.accounts.domain.services.AccountFinder;
import org.example.domain.EventBus;
import org.example.payments.transactions.domain.events.TransactionSent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class VerifyAccountsExistOnTransactionSent {

  private final AccountFinder finder;
  private final EventBus eventBus;

  public VerifyAccountsExistOnTransactionSent(AccountFinder finder, EventBus eventBus) {
    this.finder = finder;
    this.eventBus = eventBus;
  }

  @EventListener
  public void on(TransactionSent event) {
    if (bothAccountsExistFrom(event)) {
      eventBus.publish(
          Collections.singletonList(
              new AccountsVerifiedOnTransactionSent(
                  event.aggregateId(),
                  event.originAccountId(),
                  event.destinationAccountId(),
                  event.amount())));
    }
  }

  private boolean bothAccountsExistFrom(TransactionSent event) {
    AccountId origin = new AccountId(event.originAccountId());
    AccountId destination = new AccountId(event.destinationAccountId());

    return finder.existsById(origin) && finder.existsById(destination);
  }
}
