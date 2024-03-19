package org.example.payments.transactions.application.send;

import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.services.AccountFinder;
import org.example.domain.EventBus;
import org.example.payments.transactions.domain.Transaction;
import org.example.payments.transactions.domain.TransactionRepository;
import org.example.payments.transactions.infrastructure.controller.dto.CreateTransactionRequest;
import org.springframework.stereotype.Service;

@Service
public class TransactionSender {

  private final AccountFinder finder;
  private final EventBus eventBus;
  private final TransactionRepository repository;

  public TransactionSender(
      EventBus eventBus, TransactionRepository repository, AccountFinder finder) {
    this.eventBus = eventBus;
    this.repository = repository;
    this.finder = finder;
  }

  public void send(String id, CreateTransactionRequest request) {

    if (bothAccountsExistFrom(request)) {
      Transaction transaction =
          Transaction.create(
              id,
              request.originAccount(),
              request.destinationAccount(),
              request.amount(),
              request.description());

      repository.save(transaction);
      eventBus.publish(transaction.pullDomainEvents());
    }
  }

  private boolean bothAccountsExistFrom(CreateTransactionRequest request) {
    AccountId origin = new AccountId(request.originAccount());
    AccountId destination = new AccountId(request.destinationAccount());

    return finder.existsById(origin) && finder.existsById(destination);
  }
}
