package org.example.balances.infrastructure.controller;

import org.example.balances.application.BalanceFinder;
import org.example.balances.infrastructure.controller.dto.BalanceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceGetController {

  private final BalanceFinder finder;

  public BalanceGetController(BalanceFinder finder) {
    this.finder = finder;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/balances/{accountId}")
  public ResponseEntity<?> getBalance(@PathVariable String accountId) {
    return finder
        .find(accountId)
        .fold(
            error -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(error),
            balance -> ResponseEntity.ok(new BalanceResponse(balance.amount().value())));
  }
}
