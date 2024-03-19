package org.example.accounts.infrastructure.controller.dto;

import org.example.accounts.infrastructure.controller.put.dto.AccountCreateRequest;

public class AccountCreateRequestMother {

  public static AccountCreateRequest random() {
    return new AccountCreateRequest("name", "email", "phone", "dni");
  }
}
