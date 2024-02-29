package org.example.accounts.domain.events;

import org.example.domain.DomainEvent;

import java.util.Objects;

public class AccountsVerifiedOnTransactionSent extends DomainEvent {

    private final String origin;
    private final String destination;
    private final Integer amount;
    public AccountsVerifiedOnTransactionSent(String aggregateId, String origin, String destination, Integer amount) {
        super(aggregateId);
        this.origin = origin;
        this.destination = destination;
        this.amount = amount;
    }

    @Override
    public String eventName() {
        return null;
    }

    public String origin() {
        return origin;
    }

    public String destination() {
        return destination;
    }

    public Integer amount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountsVerifiedOnTransactionSent that = (AccountsVerifiedOnTransactionSent) o;
        return Objects.equals(origin, that.origin) && Objects.equals(destination, that.destination) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destination, amount);
    }
}
