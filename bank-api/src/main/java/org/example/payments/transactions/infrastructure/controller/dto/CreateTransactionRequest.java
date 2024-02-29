package org.example.payments.transactions.infrastructure.controller.dto;

public record CreateTransactionRequest(String originAccount, String destinationAccount, Integer amount, String description) {
}
