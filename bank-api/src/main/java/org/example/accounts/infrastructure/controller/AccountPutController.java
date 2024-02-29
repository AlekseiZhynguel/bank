package org.example.accounts.infrastructure.controller;

import org.example.accounts.application.create.AccountCreator;
import org.example.accounts.infrastructure.controller.dto.AccountCreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountPutController {

    private final AccountCreator creator;

    public AccountPutController(AccountCreator creator) {
        this.creator = creator;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value = "/accounts/{id}")
    public void create(@PathVariable String id, @RequestBody AccountCreateRequest request) {
        creator.create(id, request.name(), request.email(), request.phone(), request.dni());
    }
}
