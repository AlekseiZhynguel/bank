package org.example.accounts.domain.exceptions;

import org.example.accounts.domain.AccountId;
import org.example.domain.DomainException;

public class AccountNotFoundException extends DomainException {

    private final AccountId id;


    public AccountNotFoundException(AccountId id) {
        this.id = id;
    }
}
