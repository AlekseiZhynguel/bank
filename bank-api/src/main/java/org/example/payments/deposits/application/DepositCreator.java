package org.example.payments.deposits.application;

import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.services.AccountFinder;
import org.example.domain.EventBus;
import org.example.payments.deposits.domain.Deposit;
import org.example.payments.deposits.domain.DepositRepository;
import org.example.payments.deposits.infrastructure.controller.dto.CreateDepositRequest;
import org.springframework.stereotype.Service;

@Service
public class DepositCreator {

  private final AccountFinder accountFinder;
  private final DepositRepository repository;
  private final EventBus eventBus;

  public DepositCreator(
      AccountFinder accountFinder, DepositRepository repository, EventBus eventBus) {
    this.accountFinder = accountFinder;
    this.repository = repository;
    this.eventBus = eventBus;
  }

  public void deposit(String id, CreateDepositRequest request) {

    if (existsAccountWith(request.destinationAccount())) {
      Deposit deposit =
          Deposit.create(id, request.destinationAccount(), request.amount(), request.description());

      repository.save(deposit);
      eventBus.publish(deposit.pullDomainEvents());
    }
  }

  private boolean existsAccountWith(String id) {
    return accountFinder.existsById(new AccountId(id));
  }
}
