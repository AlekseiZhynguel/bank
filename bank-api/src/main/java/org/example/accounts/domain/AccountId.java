package org.example.accounts.domain;

import java.util.UUID;
import org.example.accounts.domain.exceptions.AccountIdNotUuid;

public record AccountId(String value) {

  public AccountId {
    ensureIsValidUuid(value);
  }

  private void ensureIsValidUuid(String value) {
    try {
      UUID.fromString(value);
    } catch (Exception exception) {
      throw new AccountIdNotUuid(value);
    }
  }
}
