package org.example.balances.domain;

import org.example.accounts.domain.AccountId;

import java.util.Optional;

public interface BalanceRepository {
  void save(Balance balance);

  Optional<Balance> findByAccountId(AccountId id);
}
