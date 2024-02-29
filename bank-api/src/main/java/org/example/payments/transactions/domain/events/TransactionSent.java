package org.example.payments.transactions.domain.events;

import org.example.domain.DomainEvent;

import java.util.Objects;

public class TransactionSent extends DomainEvent {

    private final String originAccountId;
    private final String destinationAccountId;
    public TransactionSent(String aggregateId, String originAccountId, String destinationAccountId) {
        super(aggregateId);
        this.originAccountId = originAccountId;
        this.destinationAccountId = destinationAccountId;
    }

    @Override
    public String eventName() {
        return null;
    }

    public String originAccountId() {
        return originAccountId;
    }

    public String destinationAccountId() {
        return destinationAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionSent that = (TransactionSent) o;
        return Objects.equals(originAccountId, that.originAccountId) && Objects.equals(destinationAccountId, that.destinationAccountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originAccountId, destinationAccountId);
    }
}
