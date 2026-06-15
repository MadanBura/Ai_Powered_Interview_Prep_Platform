package com.interview.platform.mod01_authentication;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(JwtTokenManagementController.class)
@org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc(addFilters = false)
class JwtTokenManagementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtTokenManagementService service;

    @Test
    @DisplayName("TC-01-02-01: Valid refresh token")
    void whenValidRefreshToken_TC010201() throws Exception {
        String requestBody = "{\"refreshToken\":\"dGhpcyBpcyBhIHJlZnJlc2ggdG9rZW4...\"}";
        
        mockMvc.perform(post("/api/v1/auth/token/refresh")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("TC-01-02-02: Expired refresh token")
    void whenExpiredRefreshToken_TC010202() throws Exception {
        String requestBody = "{\"refreshToken\":\"dGhpcyBpcyBhIHJlZnJlc2ggdG9rZW4...\"}";
        
        doThrow(new SecurityException("Expired refresh token")).when(service).refreshToken(any());

        mockMvc.perform(post("/api/v1/auth/token/refresh")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("TC-01-02-03: Invalid/Malformed refresh token")
    void whenInvalidRefreshToken_TC010203() throws Exception {
        String requestBody = "{\"refreshToken\":\"dGhpcyBpcyBhIHJlZnJlc2ggdG9rZW4...\"}";
        
        doThrow(new IllegalArgumentException("Invalid token")).when(service).refreshToken(any());

        mockMvc.perform(post("/api/v1/auth/token/refresh")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("TC-01-02-04: Successful logout")
    void whenSuccessfulLogout_TC010204() throws Exception {
        String requestBody = "{\"refreshToken\":\"dGhpcyBpcyBhIHJlZnJlc2ggdG9rZW4...\"}";
        
        mockMvc.perform(post("/api/v1/auth/logout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }
}
