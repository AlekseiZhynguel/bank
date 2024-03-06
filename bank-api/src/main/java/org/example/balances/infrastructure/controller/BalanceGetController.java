package org.example.balances.infrastructure.controller;

import java.util.Optional;
import java.util.function.Function;
import org.example.balances.application.BalanceFinder;
import org.example.balances.infrastructure.controller.dto.BalanceResponse;
import org.example.infrastructure.controller.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceGetController extends BaseController {

  public static final String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";
  private final BalanceFinder finder;

  public BalanceGetController(BalanceFinder finder) {
    this.finder = finder;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/balances/{accountId}")
  public ResponseEntity<String> getBalance(@PathVariable String accountId) {
    return finder
        .find(accountId)
        .fold(
            error -> buildErrorJsonResponse(error.message(), error.id()),
            balance -> {
              BalanceResponse response = new BalanceResponse(balance.amount().value());
              return buildSuccessResponseJsonResponse(response);
            });
  }

  private ResponseEntity<String> buildErrorJsonResponse(String errorMessage, String id) {
    return Optional.of(errorMessage)
        .filter(RESOURCE_NOT_FOUND::equals)
        .map(buildResourceNotFoundError(id))
        .orElseThrow(() -> new RuntimeException("Unknown exception"));
  }

  private Function<String, ResponseEntity<String>> buildResourceNotFoundError(String id) {
    return errorMessage ->
        buildErrorResponse(
            RESOURCE_NOT_FOUND,
            "The requested resource was not found.",
            HttpStatus.NOT_FOUND.value(),
            String.format("The user with the ID '%s' does not exist in our records.", id),
            String.format("/balances/%s", id),
            "Please check if the user ID is correct or refer to our documentation at https://api.example.com/docs/errors#RESOURCE_NOT_FOUND for more information.");
  }
}
