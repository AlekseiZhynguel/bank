package org.example.infrastructure.controller.dto;

public record ErrorDetail(
    String code, String message, String details, String path, String suggestion) {}
