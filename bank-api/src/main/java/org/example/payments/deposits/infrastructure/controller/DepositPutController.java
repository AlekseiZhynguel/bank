package org.example.payments.deposits.infrastructure.controller;

import org.example.payments.deposits.application.DepositCreator;
import org.example.payments.deposits.infrastructure.controller.dto.CreateDepositRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepositPutController {

  private final DepositCreator depositor;

  public DepositPutController(DepositCreator depositor) {
    this.depositor = depositor;
  }

  @PutMapping(value = "/deposits/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public void createDeposit(@PathVariable String id, @RequestBody CreateDepositRequest request) {
    depositor.deposit(id, request);
  }
}
