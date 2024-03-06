package org.example.accounts.infrastructure.controller.put.dto;

public record AccountCreateRequest(String name, String email, String phone, String dni) {}
