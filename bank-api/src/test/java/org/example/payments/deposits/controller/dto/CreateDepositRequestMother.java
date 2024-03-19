package org.example.payments.deposits.controller.dto;

import org.example.payments.deposits.infrastructure.controller.dto.CreateDepositRequest;

public class CreateDepositRequestMother {

  public static CreateDepositRequest random() {
    return new CreateDepositRequest("destination", 100, "description");
  }
}
