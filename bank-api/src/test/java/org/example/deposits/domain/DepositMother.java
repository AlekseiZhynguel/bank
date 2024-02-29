package org.example.deposits.domain;

import org.example.accounts.domain.AccountId;
import org.example.accounts.domain.AccountIdMother;
import org.example.payments.deposits.domain.primitives.DepositPrimitives;
import org.example.payments.deposits.domain.Deposit;
import org.example.payments.deposits.domain.DepositAmount;
import org.example.payments.deposits.domain.DepositDescription;
import org.example.payments.deposits.domain.DepositId;

public class DepositMother {

    public static Deposit create(DepositId id, AccountId destinationId, DepositAmount amount, DepositDescription description) {
        return Deposit.from(
                new DepositPrimitives(
                        id.value(),
                        destinationId.value(),
                        amount.value(),
                        description.value()
                )
        );
    }

    public static Deposit random() {
        return create(
                DepositIdMother.random(),
                AccountIdMother.random(),
                DepositAmountMother.random(),
                DepositDescriptionMother.random()
        );
    }
}
