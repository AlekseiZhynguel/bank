package org.example.balances.infrastructure.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.example.accounts.domain.AccountId;
import org.example.balances.domain.Balance;
import org.example.balances.domain.BalanceRepository;
import org.example.balances.domain.primitives.BalancePrimitives;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBalanceRepository implements BalanceRepository {

  public static final BalancePrimitives testBalances =
      new BalancePrimitives(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 1000);
  private Map<String, BalancePrimitives> balances = new HashMap<>();

  public static InMemoryBalanceRepository withSomeBalances() {
    InMemoryBalanceRepository repository = new InMemoryBalanceRepository();

    repository.balances.put(testBalances.id(), testBalances);

    return repository;
  }

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
