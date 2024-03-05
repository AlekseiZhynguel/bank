package org.example.payments.deposits.application;

import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.services.AccountFinder;
import org.example.domain.EventBus;
import org.example.payments.deposits.domain.Deposit;
import org.example.payments.deposits.domain.DepositRepository;
import org.springframework.stereotype.Service;

@Service
public class FundsDepositor {

  private final AccountFinder accountFinder;
  private final DepositRepository repository;
  private final EventBus eventBus;

  public FundsDepositor(
      AccountFinder accountFinder, DepositRepository repository, EventBus eventBus) {
    this.accountFinder = accountFinder;
    this.repository = repository;
    this.eventBus = eventBus;
  }

  public void deposit(String id, String destinationAccount, Integer amount, String description) {
    Deposit deposit = Deposit.create(id, destinationAccount, amount, description);

    if (existsAccountWith(deposit.destinationAccount())) {
      repository.save(deposit);
      eventBus.publish(deposit.pullDomainEvents());
    }
  }

  private boolean existsAccountWith(AccountId id) {
    return accountFinder.existsById(id);
  }
}
