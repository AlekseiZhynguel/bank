package org.example.balances.domain;

import java.util.Random;

public class BalanceAmountMother {

  public static BalanceAmount create(Integer value) {
    return new BalanceAmount(value);
  }

  public static BalanceAmount random() {
    return create(new Random().nextInt());
  }
}
