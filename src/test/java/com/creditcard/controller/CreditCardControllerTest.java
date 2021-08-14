package com.creditcard.controller;


import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class CreditCardControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void indexTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/info").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Server is running!")));
    }

    @Test
    public void shouldSaveCardDetails() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/creditCard/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Home\",\"creditCardNumber\":\"4596-0690-9685-2253\",\"balance\":1000,\"limit\":2000}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(equalTo("SUCCESS")));
    }
    @Test
    public void shouldFetchAllCardDetails() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/creditCard/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Home\",\"creditCardNumber\":\"4852789106979280262\",\"balance\":1000,\"limit\":2000}"));
        mvc.perform(MockMvcRequestBuilders.get("/creditCard/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string("[{\"name\":\"Home\",\"creditCardNumber\":\"4852789106979280262\",\"balance\":1000,\"limit\":2000}]"));
    }

    @Test
    public void invalidCardNumberBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/creditCard/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Home\",\"creditCardNumber\":\"11111111111111112\",\"balance\":1000,\"limit\":2000}"));
        mvc.perform(MockMvcRequestBuilders.get("/creditCard/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andExpect(content().string("{\"error\":{\"statusCode\":22001,\"code\":\"ERR_INVALID_CARD\",\"message\":\"Invalid Card number\"}}"));
    }

    @Test
    public void cardNumberExistBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/creditCard/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Home\",\"creditCardNumber\":\"4852789106979280262\",\"balance\":1000,\"limit\":2000}"));
        mvc.perform(MockMvcRequestBuilders.post("/creditCard/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Home\",\"creditCardNumber\":\"4852789106979280262\",\"balance\":1000,\"limit\":2000}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{\"error\":{\"statusCode\":22002,\"code\":\"ERR_RECORD_EXIST\",\"message\":\"Credit Card number is already exist\"}}"));
    }
}