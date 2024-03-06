package org.example.balances.application;

import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.events.AccountsVerifiedOnTransactionSent;
import org.example.accounts.domain.exceptions.AccountNotFoundException;
import org.example.balances.domain.Balance;
import org.example.balances.domain.BalanceRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class DecreaseBalanceOnTransactionSent {

  private final BalanceRepository repository;

  public DecreaseBalanceOnTransactionSent(BalanceRepository repository) {
    this.repository = repository;
  }

  @EventListener
  public void on(AccountsVerifiedOnTransactionSent event) {
    AccountId origin = new AccountId(event.origin());
    Balance balance =
        repository
            .findByAccountId(origin)
            .orElseThrow(() -> new AccountNotFoundException(origin.value()));

    balance.decreaseIn(event.amount());

    repository.save(balance);
  }
}
