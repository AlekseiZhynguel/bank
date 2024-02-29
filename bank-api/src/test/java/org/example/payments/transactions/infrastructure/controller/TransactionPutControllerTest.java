package org.example.payments.transactions.infrastructure.controller;

import org.example.payments.transactions.application.send.TransactionSender;
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
@WebMvcTest(controllers = TransactionPutController.class)
class TransactionPutControllerTest {

    @MockBean
    private TransactionSender useCase;
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldCreateATransaction() throws Exception {
        mockMvc.perform(
                put("/transactions/id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "origin_account": "origin",
                                    "destination_account": "destination",
                                    "amount": 100,
                                    "description": "description"
                                }
                                """)
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }
}