package org.example.accounts.domain.exceptions;

import org.example.accounts.domain.AccountId;
import org.example.domain.DomainException;

public class AccountNotFoundException extends DomainException {

  public AccountNotFoundException(AccountId id) {
    super("account_not_found", String.format("The id <%s> was not found", id.value()));
  }
}
