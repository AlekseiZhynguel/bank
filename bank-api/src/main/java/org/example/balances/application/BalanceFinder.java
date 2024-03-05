package org.example.balances.application;

import io.vavr.control.Either;
import org.example.accounts.domain.AccountId;
import org.example.balances.domain.Balance;
import org.example.balances.domain.BalanceRepository;
import org.example.balances.infrastructure.controller.dto.BalanceNotFound;
import org.springframework.stereotype.Service;

@Service
public class BalanceFinder {

  private final BalanceRepository repository;

  public BalanceFinder(BalanceRepository repository) {
    this.repository = repository;
  }

  public Either<BalanceNotFound, Balance> find(String id) {
    return repository
        .findByAccountId(new AccountId(id))
        .<Either<BalanceNotFound, Balance>>map(Either::right)
        .orElseGet(() -> Either.left(new BalanceNotFound("Sorry. Couldn't find that balance")));
  }
}
