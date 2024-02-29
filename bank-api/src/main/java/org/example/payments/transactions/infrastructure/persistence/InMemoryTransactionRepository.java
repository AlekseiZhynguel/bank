package org.example.payments.transactions.infrastructure.persistence;

import org.example.payments.transactions.domain.Transaction;
import org.example.payments.transactions.domain.TransactionRepository;
import org.example.payments.transactions.domain.primitives.TransactionPrimitives;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryTransactionRepository implements TransactionRepository {

    private Map<String, TransactionPrimitives> transactions = new HashMap<>();
    @Override
    public void save(Transaction transaction) {
        TransactionPrimitives primitives = transaction.toPrimitives();
        transactions.put(primitives.id(), primitives);
    }
}
