package org.example.accounts.domain.events;

import org.example.accounts.domain.Account;
import org.example.accounts.domain.AccountId;

public class AccountCreatedMother {

    public static AccountCreated create(AccountId id) {
        return new AccountCreated(id.value());
    }

    public static AccountCreated fromAccount(Account account) {
        return create(account.id());
    }
}
