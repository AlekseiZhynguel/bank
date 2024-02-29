package org.example.balances.domain;

public record BalanceAmount(Integer value) {

    public static BalanceAmount zero() {
        return new BalanceAmount(0);
    }

    public BalanceAmount add(Integer other) {
        return new BalanceAmount(this.value + other);
    }
}
