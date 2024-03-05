package org.example.balances.application;

import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.exceptions.AccountNotFoundException;
import org.example.balances.domain.Balance;
import org.example.balances.domain.BalanceRepository;
import org.example.payments.deposits.domain.events.FundsDeposited;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class IncreaseBalanceOnFundsDeposited {

  private final BalanceRepository repository;

  public IncreaseBalanceOnFundsDeposited(BalanceRepository repository) {
    this.repository = repository;
  }

  @EventListener
  public void on(FundsDeposited event) {
    AccountId accountId = new AccountId(event.accountId());
    Balance balance =
        repository
            .findByAccountId(accountId)
            .orElseThrow(() -> new AccountNotFoundException(accountId));

    balance.increaseIn(event.amount());
    repository.save(balance);
  }
}
