package org.example.payments.deposits.controller;

import org.example.AcceptanceTestCase;
import org.example.accounts.domain.AccountIdMother;
import org.junit.jupiter.api.Test;

class DepositPutControllerTest extends AcceptanceTestCase {

  @Test
  void shouldDepositFunds() throws Exception {

    String accountId = AccountIdMother.random().value();
    givenThereIsAnAccount(accountId);
    String body =
        """
          {
               "destinationAccount": "%s",
               "amount": 23000,
               "description": "prueba"
           }
          """
            .formatted(accountId);

    assertRequestWithBody("PUT", "/deposits/id", body, 201);
  }

  private void givenThereIsAnAccount(String accountId) throws Exception {
    String body =
        """
          {
              "name": "name",
              "email": "email",
              "phone": "phone",
              "dni": "dni"
          }
          """;
    assertRequestWithBody("PUT", "/accounts/" + accountId, body, 201);
  }
}
