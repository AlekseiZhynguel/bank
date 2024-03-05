package org.example.payments.transactions.domain;

import java.util.Random;

public class TransactionAmountMother {

  public static TransactionAmount create(Integer value) {
    return new TransactionAmount(value);
  }

  public static TransactionAmount random() {
    return create(new Random().nextInt());
  }
}
