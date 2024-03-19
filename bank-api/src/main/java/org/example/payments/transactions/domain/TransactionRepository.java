package org.example.payments.transactions.domain;

import java.util.Optional;

public interface TransactionRepository {
  void save(Transaction transaction);

  Optional<Transaction> findById(TransactionId id);
}
