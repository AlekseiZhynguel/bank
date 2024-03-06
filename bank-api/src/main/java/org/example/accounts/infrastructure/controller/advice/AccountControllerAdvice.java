package org.example.accounts.infrastructure.controller.advice;

import org.example.domain.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountControllerAdvice {

  @ExceptionHandler(DomainException.class)
  public ResponseEntity<String> handleDomainException(DomainException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }
}
