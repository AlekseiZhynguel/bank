package org.example.payments.transactions.domain;

import java.util.UUID;

public class TransactionIdMother {

  public static TransactionId create(String value) {
    return new TransactionId(value);
  }

  public static TransactionId random() {
    return create(UUID.randomUUID().toString());
  }
}
