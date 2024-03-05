package org.example.balances.infrastructure.persistence;

import org.example.accounts.domain.AccountId;
import org.example.balances.domain.Balance;
import org.example.balances.domain.BalanceRepository;
import org.example.balances.domain.primitives.BalancePrimitives;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryBalanceRepository implements BalanceRepository {

  private Map<String, BalancePrimitives> balances = new HashMap<>();

  @Override
  public void save(Balance balance) {
    BalancePrimitives primitives = balance.toPrimitives();
    balances.put(primitives.id(), primitives);
  }

  @Override
  public Optional<Balance> findByAccountId(AccountId id) {
    return balances.values().stream()
        .filter(primitives -> primitives.accountId().equals(id.value()))
        .findFirst()
        .map(Balance::from);
  }
}
