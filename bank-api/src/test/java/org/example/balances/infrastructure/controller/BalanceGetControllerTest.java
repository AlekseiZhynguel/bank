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
                            "amount": 0
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
                            "message": "Sorry. Couldn't find that balance"
                        }
                        """));
  }
}
