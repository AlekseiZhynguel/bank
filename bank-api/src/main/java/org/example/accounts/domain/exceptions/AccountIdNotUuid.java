package org.example.accounts.domain.exceptions;

import org.example.domain.DomainException;

public class AccountIdNotUuid extends DomainException {
  public AccountIdNotUuid(String id) {
    super("accountId_not_uuid", String.format("The id <%s> is not an Uuid", id));
  }
}
