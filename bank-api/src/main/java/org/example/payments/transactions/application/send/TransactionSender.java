package org.example.payments.transactions.application.send;

import org.example.domain.EventBus;
import org.example.payments.transactions.domain.Transaction;
import org.example.payments.transactions.domain.TransactionRepository;
import org.example.payments.transactions.infrastructure.controller.dto.CreateTransactionRequest;
import org.springframework.stereotype.Service;

@Service
public class TransactionSender {
  private final EventBus eventBus;
  private final TransactionRepository repository;

  public TransactionSender(EventBus eventBus, TransactionRepository repository) {
    this.eventBus = eventBus;
    this.repository = repository;
  }

  public void send(String id, CreateTransactionRequest request) {

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
