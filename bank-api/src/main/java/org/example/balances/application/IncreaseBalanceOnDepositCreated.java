package org.example.balances.application;

import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.exceptions.AccountNotFoundException;
import org.example.balances.domain.Balance;
import org.example.balances.domain.BalanceRepository;
import org.example.payments.deposits.domain.events.DepositCreated;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class IncreaseBalanceOnDepositCreated {

  private final BalanceRepository repository;

  public IncreaseBalanceOnDepositCreated(BalanceRepository repository) {
    this.repository = repository;
  }

  @EventListener
  public void on(DepositCreated event) {
    AccountId accountId = new AccountId(event.accountId());
    Balance balance =
        repository
            .findByAccountId(accountId)
            .orElseThrow(() -> new AccountNotFoundException(accountId.value()));

    balance.increaseIn(event.amount());
    repository.save(balance);
  }
}
