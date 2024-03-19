package org.example.payments.deposits.controller.dto;

import java.util.UUID;
import org.example.payments.deposits.infrastructure.controller.dto.CreateDepositRequest;

public class CreateDepositRequestMother {

  public static CreateDepositRequest random() {
    return new CreateDepositRequest(UUID.randomUUID().toString(), 100, "description");
  }
}
