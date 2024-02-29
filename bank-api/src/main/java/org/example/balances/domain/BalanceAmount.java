package org.example.balances.domain;

public record BalanceAmount(Integer value) {

    public static BalanceAmount zero() {
        return new BalanceAmount(0);
    }

    public BalanceAmount add(Integer other) {
        return new BalanceAmount(this.value + other);
    }

    public BalanceAmount subtract(Integer other) {
        int result = this.value - other;
        if (result < 0) throw new IllegalArgumentException("Insufficient funds!!");
        return new BalanceAmount(result);
    }
}
