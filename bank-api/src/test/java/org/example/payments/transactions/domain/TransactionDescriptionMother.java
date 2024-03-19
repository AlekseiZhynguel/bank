package org.example.payments.transactions.domain;

public class TransactionDescriptionMother {

  public static TransactionDescription create(String value) {
    return new TransactionDescription(value);
  }

  public static TransactionDescription random() {
    return create("random");
  }
}
