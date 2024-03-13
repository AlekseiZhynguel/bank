package org.example.balances.infrastructure.controller;

import org.example.AcceptanceTestCase;
import org.example.accounts.domain.AccountIdMother;
import org.example.balances.domain.BalanceIdMother;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BalanceGetControllerTest extends AcceptanceTestCase {

  @Test
  void shouldReturnAValidBalanceWhenAnAccountIsCreated() throws Exception {
    String accountId = AccountIdMother.random().value();
    givenThereIsAnAccount(accountId);
    String response =
        """
            {
                 "status": "success",
                 "statusCode": 200,
                 "payload": {
                     "amount": 0
                 },
                 "documentation_url": "https://api.example.com/docs"
             }
            """;

    assertResponse("/balances/" + accountId, 200, response);
  }

  @Test
  void shouldReturnAMessageErrorWhenNoAccountIsPreviouslyCreated() throws Exception {
    String balanceId = BalanceIdMother.random().value();

    String response =
        """
          {
               "status": "error",
               "statusCode": 404,
               "error": {
                   "code": "RESOURCE_NOT_FOUND",
                   "message": "The requested resource was not found.",
                   "details": "The user with the ID '%s' does not exist in our records.",
                   "path": "/balances/%s",
                   "suggestion": "Please check if the user ID is correct or refer to our documentation at https://api.example.com/docs/errors#RESOURCE_NOT_FOUND for more information."
               },
               "documentation_url": "https://api.example.com/docs/errors"
           }
          """
            .formatted(balanceId, balanceId);

    assertResponse("/balances/" + balanceId, 404, response);
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
