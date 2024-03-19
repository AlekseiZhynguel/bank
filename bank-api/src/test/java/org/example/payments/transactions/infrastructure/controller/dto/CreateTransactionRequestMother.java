package org.example.payments.transactions.infrastructure.controller.dto;

import java.util.UUID;

public class CreateTransactionRequestMother {

  public static CreateTransactionRequest random() {
    return new CreateTransactionRequest(
        UUID.randomUUID().toString(), UUID.randomUUID().toString(), 100, "description");
  }
}
