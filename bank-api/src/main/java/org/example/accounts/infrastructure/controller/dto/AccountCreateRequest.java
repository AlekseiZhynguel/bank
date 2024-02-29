package org.example.accounts.infrastructure.controller.dto;

public record AccountCreateRequest(String name, String email, String phone, String dni) {
}
