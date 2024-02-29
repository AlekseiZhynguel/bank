package org.example.payments.transactions.domain.primitives;

public record TransactionPrimitives(String id, String originAccount, String destinationAccount, Integer amount, String description) {
}
