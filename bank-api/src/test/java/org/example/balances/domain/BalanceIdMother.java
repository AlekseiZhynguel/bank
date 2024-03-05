package org.example.balances.domain;

import java.util.UUID;

public class BalanceIdMother {

  public static BalanceId create(String value) {
    return new BalanceId(value);
  }

  public static BalanceId random() {
    return create(UUID.randomUUID().toString());
  }
}
