package org.example.balances.application;

import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.events.AccountsVerifiedOnTransactionSent;
import org.example.accounts.domain.exceptions.AccountNotFoundException;
import org.example.balances.domain.Balance;
import org.example.balances.domain.BalanceRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class IncreaseBalanceOnTransactionReceived {

  private final BalanceRepository repository;

  public IncreaseBalanceOnTransactionReceived(BalanceRepository repository) {
    this.repository = repository;
  }

  @EventListener
  public void on(AccountsVerifiedOnTransactionSent event) {
    AccountId destination = new AccountId(event.destination());
    Balance balance =
        repository
            .findByAccountId(destination)
            .orElseThrow(() -> new AccountNotFoundException(destination.value()));

    balance.increaseIn(event.amount());

    repository.save(balance);
  }
}
