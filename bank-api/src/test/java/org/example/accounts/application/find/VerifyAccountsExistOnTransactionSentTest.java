package org.example.accounts.application.find;

import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.services.AccountFinder;
import org.example.domain.EventBus;
import org.example.payments.transactions.domain.events.TransactionSent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VerifyAccountsExistOnTransactionSentTest {

    private final EventBus eventBus = mock(EventBus.class);
    private final AccountFinder finder = mock(AccountFinder.class);
    private final VerifyAccountsExistOnTransactionSent useCase = new VerifyAccountsExistOnTransactionSent(finder, eventBus);

    @Test
    void shouldVerifyIfBothAccountsExist() {
        TransactionSent event = new TransactionSent("id", "origin", "destination", 100);
        when(finder.existsById(new AccountId("origin"))).thenReturn(true);
        when(finder.existsById(new AccountId("destination"))).thenReturn(true);
        useCase.on(event);

        verify(eventBus).publish(any());
    }

    @Test
    void shouldNotPublishAnEventWhenAnAccountDoesNotExist() {
        TransactionSent event = new TransactionSent("id", "origin", "destination", 100);
        when(finder.existsById(new AccountId("origin"))).thenReturn(true);
        when(finder.existsById(new AccountId("destination"))).thenReturn(false);
        useCase.on(event);

        verifyNoInteractions(eventBus);
    }
}