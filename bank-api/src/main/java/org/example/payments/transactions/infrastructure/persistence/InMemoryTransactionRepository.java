package org.example.payments.transactions.infrastructure.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.example.payments.transactions.domain.Transaction;
import org.example.payments.transactions.domain.TransactionId;
import org.example.payments.transactions.domain.TransactionRepository;
import org.example.payments.transactions.domain.primitives.TransactionPrimitives;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryTransactionRepository implements TransactionRepository {

  private Map<String, TransactionPrimitives> transactions = new HashMap<>();

  @Override
  public void save(Transaction transaction) {
    TransactionPrimitives primitives = transaction.toPrimitives();
    transactions.put(primitives.id(), primitives);
  }

  @Override
  public Optional<Transaction> findById(TransactionId id) {
    return Optional.ofNullable(transactions.get(id.value())).map(Transaction::from);
  }
}
