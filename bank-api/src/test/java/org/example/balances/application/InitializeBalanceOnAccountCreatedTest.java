package org.example.balances.application;

import org.example.accounts.domain.events.AccountCreated;
import org.example.balances.domain.Balance;
import org.example.balances.domain.BalanceRepository;
import org.example.balances.application.InitializeBalanceOnAccountCreated;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InitializeBalanceOnAccountCreatedTest {

  private final BalanceRepository repository = mock(BalanceRepository.class);
  private final InitializeBalanceOnAccountCreated useCase =
      new InitializeBalanceOnAccountCreated(repository);

  @Test
  void shouldInitializeBalance() {

    AccountCreated event = new AccountCreated("id");
    useCase.on(event);

    verify(repository).save(isA(Balance.class));
  }
}
