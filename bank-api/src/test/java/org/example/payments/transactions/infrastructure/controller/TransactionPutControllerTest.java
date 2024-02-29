package org.example.payments.transactions.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionPutControllerTest {

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