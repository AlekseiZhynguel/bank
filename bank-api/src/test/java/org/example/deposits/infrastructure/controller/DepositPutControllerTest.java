package org.example.deposits.infrastructure.controller;

import org.example.payments.deposits.application.FundsDepositor;
import org.example.payments.deposits.infrastructure.controller.DepositPutController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DepositPutController.class)
class DepositPutControllerTest {

    @MockBean
    private FundsDepositor useCase;
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldDepositFunds() throws Exception {
        mockMvc.perform(
                put("/deposits/id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                     "destinationAccount": "prueba",
                                     "amount": 23000,
                                     "description": "prueba"
                                 }
                                """))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}