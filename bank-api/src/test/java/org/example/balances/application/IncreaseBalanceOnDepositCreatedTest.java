package org.example.balances.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.exceptions.AccountNotFoundException;
import org.example.balances.domain.BalanceAmount;
import org.example.balances.infrastructure.persistence.InMemoryBalanceRepository;
import org.example.payments.deposits.domain.DepositAmount;
import org.example.payments.deposits.domain.events.DepositCreatedMother;
import org.junit.jupiter.api.Test;

class IncreaseBalanceOnDepositCreatedTest {

  private final AccountId accountId =
      new AccountId(InMemoryBalanceRepository.testBalances.accountId());
  private final BalanceAmount balanceAmount =
      new BalanceAmount(InMemoryBalanceRepository.testBalances.amount());

  @Test
  void shouldIncreaseBalance() {
    var repository = InMemoryBalanceRepository.withSomeBalances();
    var useCase = new IncreaseBalanceOnDepositCreated(repository);
    var event = DepositCreatedMother.withAccountIdAndAmount(accountId, new DepositAmount(100));

    useCase.on(event);

    var response = repository.findByAccountId(accountId).get();
    assertEquals(response.amount(), balanceAmount.add(100));
  }

  @Test
  void shouldThrowAnExceptionWhenAccountNotFound() {
    var repository = new InMemoryBalanceRepository();
    var useCase = new IncreaseBalanceOnDepositCreated(repository);
    var event = DepositCreatedMother.random();

    assertThrows(AccountNotFoundException.class, () -> useCase.on(event));
  }
}
