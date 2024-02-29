package org.example.accounts.domain;

import org.example.accounts.domain.primitives.AccountPrimitives;

public class AccountMother {

    public static Account create(AccountId id, AccountName name, AccountEmail email, AccountPhone phone, AccountDni dni) {
        return Account.from(new AccountPrimitives(id.value(), name.value(), email.value(), phone.value(), dni.value()));
    }

    public static Account random() {
        return create(
                AccountIdMother.random(),
                AccountNameMother.random(),
                AccountEmailMother.random(),
                AccountPhoneMother.random(),
                AccountDniMother.random());
    }
}
