package org.example.balances.application;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.exceptions.AccountNotFoundException;
import org.example.balances.domain.Balance;
import org.example.balances.domain.BalanceMother;
import org.example.balances.domain.BalanceRepository;
import org.example.payments.deposits.domain.events.FundsDeposited;
import org.example.payments.deposits.domain.events.FundsDepositedMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class IncreaseBalanceOnFundsDepositedTest {
  BalanceRepository repository = mock(BalanceRepository.class);
  IncreaseBalanceOnFundsDeposited useCase = new IncreaseBalanceOnFundsDeposited(repository);

  @Test
  void shouldIncreaseBalance() {
    FundsDeposited event = FundsDepositedMother.random();
    Balance balance = BalanceMother.random();
    when(repository.findByAccountId(new AccountId(event.accountId())))
        .thenReturn(Optional.of(balance));

    useCase.on(event);

    verify(repository).findByAccountId(new AccountId(event.accountId()));
    verify(repository).save(balance);
  }

  @Test
  void shouldThrowAnExceptionWhenAccountNotFound() {
    FundsDeposited event = FundsDepositedMother.random();
    assertThrows(AccountNotFoundException.class, () -> useCase.on(event));
  }
}
