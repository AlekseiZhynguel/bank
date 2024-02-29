package org.example.accounts.infrastructure.controller;

import org.example.accounts.application.create.AccountCreator;
import org.example.payments.transactions.infrastructure.controller.TransactionPutController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountPutController.class)
class AccountPutControllerTest {

    @MockBean
    private AccountCreator useCase;
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldCreateAnAccount() throws Exception {
        mockMvc.perform(
                put("/accounts/id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "name",
                                    "email": "email",
                                    "phone": "phone",
                                    "dni": "dni"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated());

    }

}