package org.example.payments.deposits.infrastructure.controller.dto;

public record CreateDepositRequest (String destinationAccount, Integer amount, String description) {
}
