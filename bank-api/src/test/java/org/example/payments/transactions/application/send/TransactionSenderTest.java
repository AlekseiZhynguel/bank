package org.example.payments.transactions.application.send;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.infrastructure.events.noop.EventBusNoop;
import org.example.payments.transactions.domain.Transaction;
import org.example.payments.transactions.domain.TransactionId;
import org.example.payments.transactions.domain.TransactionIdMother;
import org.example.payments.transactions.domain.TransactionMother;
import org.example.payments.transactions.infrastructure.controller.dto.CreateTransactionRequest;
import org.example.payments.transactions.infrastructure.controller.dto.CreateTransactionRequestMother;
import org.example.payments.transactions.infrastructure.persistence.InMemoryTransactionRepository;
import org.junit.jupiter.api.Test;

class TransactionSenderTest {

  @Test
  void shouldSendATransaction() {

    var eventBus = new EventBusNoop();
    var repository = new InMemoryTransactionRepository();
    var useCase = new TransactionSender(eventBus, repository);

    TransactionId id = TransactionIdMother.random();
    CreateTransactionRequest request = CreateTransactionRequestMother.random();
    Transaction transaction = TransactionMother.fromRequest(id, request);

    useCase.send(id.value(), request);

    Transaction expected = repository.findById(transaction.id()).get();

    assertEquals(expected, transaction);
  }
}
