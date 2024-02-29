package org.example.payments.deposits.infrastructure.controller;

import org.example.payments.deposits.application.FundsDepositor;
import org.example.payments.deposits.infrastructure.controller.dto.CreateDepositRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepositPutController {

    private final FundsDepositor depositor;

    public DepositPutController(FundsDepositor depositor) {
        this.depositor = depositor;
    }

    @PutMapping(value = "/deposits/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDeposit(@PathVariable String id, @RequestBody CreateDepositRequest request) {
        depositor.deposit(id, request.destinationAccount(), request.amount(), request.description());
    }
}
