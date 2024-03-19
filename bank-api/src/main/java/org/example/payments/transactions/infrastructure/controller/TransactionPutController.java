package org.example.payments.transactions.infrastructure.controller;

import org.example.payments.transactions.application.send.TransactionSender;
import org.example.payments.transactions.infrastructure.controller.dto.CreateTransactionRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionPutController {

  private final TransactionSender sender;

  public TransactionPutController(TransactionSender sender) {
    this.sender = sender;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PutMapping("/transactions/{id}")
  public void sendTransaction(
      @PathVariable String id, @RequestBody CreateTransactionRequest request) {
    sender.send(id, request);
  }
}
