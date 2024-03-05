package org.example.accounts.application.create;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import org.example.accounts.domain.Account;
import org.example.accounts.domain.AccountMother;
import org.example.accounts.domain.AccountRepository;
import org.example.accounts.domain.events.AccountCreated;
import org.example.accounts.domain.events.AccountCreatedMother;
import org.example.accounts.domain.exceptions.AccountIdNotUuid;
import org.example.domain.EventBus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountCreatorTest {
  private final AccountRepository repository = mock(AccountRepository.class);
  private final EventBus eventBus = mock(EventBus.class);
  private final AccountCreator creator = new AccountCreator(repository, eventBus);

  @Test
  void shouldCreateAnAccount() {

    Account account = AccountMother.random();
    AccountCreated event = AccountCreatedMother.fromAccount(account);

    creator.create(
        account.id().value(),
        account.name().value(),
        account.email().value(),
        account.phone().value(),
        account.dni().value());

    verify(repository).save(account);
    verify(eventBus).publish(Collections.singletonList(event));
  }

  @Test
  void shouldThrowException() {

    assertThrows(
        AccountIdNotUuid.class,
        () -> {
          Account account = AccountMother.withInvalidId();
          creator.create(
              account.id().value(),
              account.name().value(),
              account.email().value(),
              account.phone().value(),
              account.dni().value());
        });
  }
}
