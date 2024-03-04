package org.example.balances.infrastructure.controller;

import io.vavr.control.Either;
import org.example.balances.application.BalanceFinder;
import org.example.balances.domain.Balance;
import org.example.balances.infrastructure.controller.dto.BalanceNotFound;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BalanceGetController.class)
class BalanceGetControllerTest {

    @MockBean
    private BalanceFinder finder;
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnAValidBalance() throws Exception {
        Either<BalanceNotFound, Balance> response = Either.right(Balance.zeroFor("id", "accountId"));
        when(finder.find(anyString())).thenReturn(response);

        mockMvc.perform(
                        get("/balances/id")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "amount": 0
                        }
                        """));
    }

    @Test
    void shouldReturnAMessageError() throws Exception {
        Either<BalanceNotFound, Balance> response = Either.left(new BalanceNotFound("Sorry. Couldn't find that balance"));
        when(finder.find(anyString())).thenReturn(response);

        mockMvc.perform(
                        get("/balances/id")
                )
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().json("""
                        {
                            "message": "Sorry. Couldn't find that balance"
                        }
                        """));
    }
}