package org.example.deposits.domain;

import org.example.payments.deposits.domain.DepositDescription;

public class DepositDescriptionMother {

    public static DepositDescription create(String value) {
        return new DepositDescription(value);
    }
    public static DepositDescription random() {
        return create("random");
    }
}
