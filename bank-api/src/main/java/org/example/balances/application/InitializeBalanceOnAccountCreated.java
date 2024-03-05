package org.example.balances.application;

import org.example.accounts.domain.events.AccountCreated;
import org.example.balances.domain.Balance;
import org.example.balances.domain.BalanceRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InitializeBalanceOnAccountCreated {

  private final BalanceRepository repository;

  public InitializeBalanceOnAccountCreated(BalanceRepository repository) {
    this.repository = repository;
  }

  @EventListener
  public void on(AccountCreated event) {
    String balanceId = UUID.randomUUID().toString();
    String accountId = event.aggregateId();
    Balance balance = Balance.zeroFor(balanceId, accountId);

    repository.save(balance);
  }
}
