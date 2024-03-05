package org.example.payments.deposits.domain;

import org.example.payments.deposits.domain.DepositAmount;

import java.util.Random;

public class DepositAmountMother {

  public static DepositAmount create(Integer value) {
    return new DepositAmount(value);
  }

  public static DepositAmount random() {
    return create(new Random().nextInt());
  }
}
