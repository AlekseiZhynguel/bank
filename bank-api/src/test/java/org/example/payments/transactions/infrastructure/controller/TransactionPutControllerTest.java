package org.example.payments.transactions.infrastructure.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.example.payments.transactions.application.send.TransactionSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TransactionPutController.class)
class TransactionPutControllerTest {

  @Autowired MockMvc mockMvc;
  @MockBean private TransactionSender useCase;

  @Test
  void shouldCreateATransaction() throws Exception {
    mockMvc
        .perform(
            put("/transactions/id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                                {
                                    "originAccount": "origin",
                                    "destinationAccount": "destination",
                                    "amount": 100,
                                    "description": "description"
                                }
                                """))
        .andDo(print())
        .andExpect(status().isCreated());
  }
}
