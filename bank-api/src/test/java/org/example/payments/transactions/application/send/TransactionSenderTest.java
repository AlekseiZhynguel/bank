package org.example.payments.transactions.application.send;

import org.example.accounts.domain.services.AccountFinder;
import org.example.domain.EventBus;
import org.example.payments.transactions.domain.Transaction;
import org.example.payments.transactions.domain.TransactionRepository;
import org.example.payments.transactions.domain.events.TransactionSent;
import org.example.payments.transactions.domain.primitives.TransactionPrimitives;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionSenderTest {

    private final AccountFinder accountFinder = mock(AccountFinder.class);
    private final EventBus eventBus = mock(EventBus.class);
    private final TransactionRepository repository = mock(TransactionRepository.class);
    private final TransactionSender useCase = new TransactionSender(accountFinder, eventBus, repository);

    @Test
    void shouldSendATransaction() {
        TransactionPrimitives primitives = new TransactionPrimitives("id", "origin", "destination", 100, "description");
        Transaction transaction = Transaction.from(primitives);
        TransactionSent event = new TransactionSent(primitives.id(), primitives.originAccount(), primitives.destinationAccount());

        when(accountFinder.existsById(transaction.originId())).thenReturn(true);
        when(accountFinder.existsById(transaction.destinationId())).thenReturn(true);

        useCase.send(primitives.id(), primitives.originAccount(), primitives.destinationAccount(), primitives.amount(), primitives.description());

        verify(accountFinder).existsById(transaction.originId());
        verify(repository).save(transaction);
        verify(eventBus).publish(Collections.singletonList(event));
    }

    @Test
    void shouldntSendATransaction() {
        TransactionPrimitives primitives = new TransactionPrimitives("id", "origin", "destination", 100, "description");
        Transaction transaction = Transaction.from(primitives);

        when(accountFinder.existsById(transaction.originId())).thenReturn(true);
        when(accountFinder.existsById(transaction.destinationId())).thenReturn(false);

        useCase.send(primitives.id(), primitives.originAccount(), primitives.destinationAccount(), primitives.amount(), primitives.description());

        verify(accountFinder).existsById(transaction.originId());
        verifyNoInteractions(repository);
        verifyNoInteractions(eventBus);
    }
}