package org.example.accounts.domain;

import java.util.UUID;

public class AccountIdMother {

  public static AccountId create(String value) {
    return new AccountId(value);
  }

  public static AccountId random() {
    return create(UUID.randomUUID().toString());
  }
}
