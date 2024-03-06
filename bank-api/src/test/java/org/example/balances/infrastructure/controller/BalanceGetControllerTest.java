package org.example.balances.infrastructure.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import org.example.accounts.domain.AccountIdMother;
import org.example.balances.domain.Balance;
import org.example.balances.domain.BalanceIdMother;
import org.example.balances.domain.BalanceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BalanceGetControllerTest {

  @Autowired MockMvc mockMvc;
  @MockBean private BalanceRepository repository;

  @Test
  void shouldReturnAValidBalance() throws Exception {
    String balanceId = BalanceIdMother.random().value();
    String accountId = AccountIdMother.random().value();
    Balance balance = Balance.zeroFor(balanceId, accountId);
    when(repository.findByAccountId(any())).thenReturn(Optional.of(balance));

    mockMvc
        .perform(get("/balances/" + balanceId))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(
            content()
                .json(
                    """
                        {
                             "status": "success",
                             "statusCode": 200,
                             "payload": {
                                 "amount": 0
                             },
                             "documentation_url": "https://api.example.com/docs"
                         }
                        """));
  }

  @Test
  void shouldReturnAMessageError() throws Exception {
    String balanceId = BalanceIdMother.random().value();
    when(repository.findByAccountId(any())).thenReturn(Optional.empty());

    mockMvc
        .perform(get("/balances/" + balanceId))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(
            content()
                .json(
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
                        .formatted(balanceId, balanceId)));
  }
}
