package org.example.balances.application;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.example.accounts.domain.Account;
import org.example.accounts.domain.AccountMother;
import org.example.accounts.domain.events.AccountCreated;
import org.example.accounts.domain.events.AccountCreatedMother;
import org.example.balances.domain.Balance;
import org.example.balances.domain.BalanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InitializeBalanceOnAccountCreatedTest {

  private final BalanceRepository repository = mock(BalanceRepository.class);
  private final InitializeBalanceOnAccountCreated useCase =
      new InitializeBalanceOnAccountCreated(repository);

  @Test
  void shouldInitializeBalance() {
    // TODO verify an actual Balance check useCase implementation
    Account account = AccountMother.random();
    AccountCreated event = AccountCreatedMother.fromAccount(account);

    useCase.on(event);

    verify(repository).save(isA(Balance.class));
  }
}
