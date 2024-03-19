package org.example.payments.transactions.application.send;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.services.AccountFinder;
import org.example.infrastructure.events.noop.EventBusNoop;
import org.example.payments.transactions.domain.Transaction;
import org.example.payments.transactions.domain.TransactionId;
import org.example.payments.transactions.domain.TransactionIdMother;
import org.example.payments.transactions.domain.TransactionMother;
import org.example.payments.transactions.infrastructure.controller.dto.CreateTransactionRequest;
import org.example.payments.transactions.infrastructure.controller.dto.CreateTransactionRequestMother;
import org.example.payments.transactions.infrastructure.persistence.InMemoryTransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionSenderTest {

  private static void bothAccountsExist(AccountFinder finder, CreateTransactionRequest request) {
    AccountId originAccount = new AccountId(request.originAccount());
    when(finder.existsById(originAccount)).thenReturn(true);

    AccountId destinationAccount = new AccountId(request.destinationAccount());
    when(finder.existsById(destinationAccount)).thenReturn(true);
  }

  @Test
  void shouldSendATransaction() {

    var finder = mock(AccountFinder.class);
    var eventBus = new EventBusNoop();
    var repository = new InMemoryTransactionRepository();
    var useCase = new TransactionSender(eventBus, repository, finder);

    TransactionId id = TransactionIdMother.random();
    CreateTransactionRequest request = CreateTransactionRequestMother.random();
    Transaction transaction = TransactionMother.fromRequest(id, request);

    bothAccountsExist(finder, request);

    useCase.send(id.value(), request);

    Transaction expected = repository.findById(transaction.id()).get();

    assertEquals(expected, transaction);
  }
}
