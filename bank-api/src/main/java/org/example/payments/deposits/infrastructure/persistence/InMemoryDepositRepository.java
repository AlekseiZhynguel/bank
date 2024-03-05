package org.example.payments.deposits.infrastructure.persistence;

import org.example.payments.deposits.domain.Deposit;
import org.example.payments.deposits.domain.DepositRepository;
import org.example.payments.deposits.domain.primitives.DepositPrimitives;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryDepositRepository implements DepositRepository {
  private Map<String, DepositPrimitives> deposits = new HashMap<>();

  @Override
  public void save(Deposit deposit) {
    DepositPrimitives primitives = deposit.toPrimitives();
    deposits.put(primitives.id(), primitives);
  }
}
