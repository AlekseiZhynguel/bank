package org.example.accounts.infrastructure.controller;

import org.example.AcceptanceTestCase;
import org.example.accounts.domain.AccountIdMother;
import org.junit.jupiter.api.Test;

class AccountPutControllerTest extends AcceptanceTestCase {

  @Test
  void shouldCreateAnAccount() throws Exception {
    String accountId = AccountIdMother.random().value();
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
