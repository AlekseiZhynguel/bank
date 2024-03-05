package org.example.accounts.application.find;

import static org.mockito.Mockito.*;

import java.util.Collections;
import org.example.accounts.domain.events.AccountsVerifiedOnTransactionSent;
import org.example.accounts.domain.events.AccountsVerifiedOnTransactionSentMother;
import org.example.accounts.domain.services.AccountFinder;
import org.example.domain.EventBus;
import org.example.payments.transactions.domain.events.TransactionSent;
import org.example.payments.transactions.domain.events.TransactionSentMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VerifyAccountsExistOnTransactionSentTest {

  private final EventBus eventBus = mock(EventBus.class);
  private final AccountFinder finder = mock(AccountFinder.class);
  private final VerifyAccountsExistOnTransactionSent useCase =
      new VerifyAccountsExistOnTransactionSent(finder, eventBus);

  @Test
  void shouldVerifyIfBothAccountsExist() {
    TransactionSent event = TransactionSentMother.random();
    AccountsVerifiedOnTransactionSent newEvent =
        AccountsVerifiedOnTransactionSentMother.fromTransactionSent(event);

    when(finder.existsById(any())).thenReturn(true);
    when(finder.existsById(any())).thenReturn(true);

    useCase.on(event);

    verify(eventBus).publish(Collections.singletonList(newEvent));
  }

  @Test
  void shouldNotPublishAnEventWhenAnAccountDoesNotExist() {
    TransactionSent event = TransactionSentMother.random();

    when(finder.existsById(any())).thenReturn(true);
    when(finder.existsById(any())).thenReturn(false);

    useCase.on(event);

    verifyNoInteractions(eventBus);
  }
}
