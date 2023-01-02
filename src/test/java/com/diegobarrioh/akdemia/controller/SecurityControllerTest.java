package com.diegobarrioh.akdemia.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void indexWhenUnAuthenticatedThenRedirect() throws Exception {
        // @formatter:off
        this.mockMvc.perform(get("/"))
                .andExpect(status().isUnauthorized());
        // @formatter:on
    }

    @Test
    @WithMockUser
    void indexWhenAuthenticatedThenOk() throws Exception {
        // @formatter:off
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk());
        // @formatter:on
    }

}