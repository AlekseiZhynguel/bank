package org.example.payments.deposits.domain.events;


import org.example.domain.DomainEvent;

import java.util.Objects;

public class FundsDeposited extends DomainEvent {

    private final String accountId;
    private final Integer amount;
    public FundsDeposited(String aggregateId, String accountId, Integer amount) {
        super(aggregateId);
        this.accountId = accountId;
        this.amount = amount;
    }

    @Override
    public String eventName() {
        return "payment.funds.deposited";
    }

    public String accountId() {
        return accountId;
    }

    public Integer amount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FundsDeposited that = (FundsDeposited) o;
        return Objects.equals(accountId, that.accountId) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, amount);
    }
}
