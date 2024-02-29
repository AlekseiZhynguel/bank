package org.example.payments.deposits.domain;

import org.example.payments.deposits.domain.DepositId;

public class DepositIdMother {

    public static DepositId create(String value) {
        return new DepositId(value);
    }
    public static DepositId random() {
        return create("random");
    }
}
