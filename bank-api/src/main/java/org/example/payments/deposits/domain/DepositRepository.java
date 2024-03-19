package org.example.payments.deposits.domain;

import java.util.Optional;

public interface DepositRepository {
  void save(Deposit deposit);

  Optional<Deposit> findById(DepositId id);
}
