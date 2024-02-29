package org.example.payments.deposits.domain;

import org.example.accounts.domain.AccountId;
import org.example.domain.AggregateRoot;
import org.example.payments.deposits.domain.events.FundsDeposited;
import org.example.payments.deposits.domain.primitives.DepositPrimitives;

import java.util.Objects;

public class Deposit extends AggregateRoot {
    private final DepositId id;
    private final AccountId destinationAccount;
    private final DepositAmount amount;
    private final DepositDescription description;

    private Deposit(String id, String destinationAccount, Integer amount, String description) {
        this.id = new DepositId(id);
        this.destinationAccount = new AccountId(destinationAccount);
        this.amount = new DepositAmount(amount);
        this.description = new DepositDescription(description);
    }

    public static Deposit create(String id, String destinationAccount, Integer amount, String description) {
        Deposit deposit = new Deposit(id, destinationAccount, amount, description);

        deposit.record(new FundsDeposited(id, destinationAccount, amount));

        return deposit;
    }

    public DepositPrimitives toPrimitives() {
        return new DepositPrimitives(id.value(), destinationAccount.value(), amount.value(), description.value());
    }

    public static Deposit from(DepositPrimitives primitives) {
        return new Deposit(
                primitives.id(),
                primitives.destinationId(),
                primitives.amount(),
                primitives.description()
        );
    }

    public DepositId id() {
        return id;
    }

    public AccountId destinationAccount() {
        return destinationAccount;
    }

    public DepositAmount amount() {
        return amount;
    }

    public DepositDescription description() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deposit deposit = (Deposit) o;
        return Objects.equals(id, deposit.id) && Objects.equals(destinationAccount, deposit.destinationAccount) && Objects.equals(amount, deposit.amount) && Objects.equals(description, deposit.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, destinationAccount, amount, description);
    }
}
