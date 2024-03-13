package org.example.payments.transactions.infrastructure.controller;

import org.example.AcceptanceTestCase;
import org.example.accounts.domain.AccountIdMother;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransactionPutControllerTest extends AcceptanceTestCase {

  @Test
  void shouldCreateATransaction() throws Exception {

    String originAccount = AccountIdMother.random().value();
    String destinationAccount = AccountIdMother.random().value();
    givenThereAreTwoAccountsAndOneWithSomeMoney(originAccount, destinationAccount);
    String body =
        """
          {
              "originAccount": "%s",
              "destinationAccount": "%s",
              "amount": 100,
              "description": "description"
          }
          """
            .formatted(originAccount, destinationAccount);

    assertRequestWithBody("PUT", "/transactions/id", body, 201);
  }

  private void givenThereAreTwoAccountsAndOneWithSomeMoney(
      String originAccount, String destinationAccount) throws Exception {
    String accountBody =
        """
              {
                  "name": "name",
                  "email": "email",
                  "phone": "phone",
                  "dni": "dni"
              }
              """;
    String depositBody =
        """
          {
               "destinationAccount": "%s",
               "amount": 23000,
               "description": "prueba"
           }
          """
            .formatted(originAccount);

    assertRequestWithBody("PUT", "/accounts/" + originAccount, accountBody, 201);
    assertRequestWithBody("PUT", "/accounts/" + destinationAccount, accountBody, 201);
    assertRequestWithBody("PUT", "/deposits/id", depositBody, 201);
  }
}
