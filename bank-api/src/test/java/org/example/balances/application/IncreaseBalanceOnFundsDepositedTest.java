package org.example.balances.application;

import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.exceptions.AccountNotFoundException;
import org.example.balances.domain.Balance;
import org.example.balances.domain.BalanceRepository;
import org.example.balances.domain.primitives.BalancePrimitives;
import org.example.payments.deposits.domain.events.FundsDeposited;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class IncreaseBalanceOnFundsDepositedTest {
  BalanceRepository repository = mock(BalanceRepository.class);
  IncreaseBalanceOnFundsDeposited useCase = new IncreaseBalanceOnFundsDeposited(repository);

  @Test
  void shouldIncreaseBalance() {
    String aggregrateId = "id";
    String accountId = "accountId";
    Integer amount = 100;
    FundsDeposited event = new FundsDeposited(aggregrateId, accountId, amount);
    Balance balance = Balance.from(new BalancePrimitives(aggregrateId, accountId, amount));
    when(repository.findByAccountId(new AccountId(accountId))).thenReturn(Optional.of(balance));

    useCase.on(event);

    verify(repository).findByAccountId(new AccountId(accountId));
    verify(repository).save(balance);
  }

  @Test
  void shouldThrowAnExceptionWhenAccountNotFound() {
    String aggregrateId = "id";
    String accountId = "accountId";
    Integer amount = 100;
    FundsDeposited event = new FundsDeposited(aggregrateId, accountId, amount);
    assertThrows(AccountNotFoundException.class, () -> useCase.on(event));
  }
}
