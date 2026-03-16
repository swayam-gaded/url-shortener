package com.darklord.url_shortener.controller;

import com.darklord.url_shortener.dto.UrlRequest;
import com.darklord.url_shortener.dto.UrlResponse;
import com.darklord.url_shortener.service.UrlService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UrlController.class)
public class UrlControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private UrlService urlService;
    @Autowired
    private ObjectMapper objectMapper;

    // A valid request hitting the POST endpoint
    @Test
    void whenPostValidUrl() throws Exception {
        UrlRequest request = new UrlRequest("https://google.com");
        UrlResponse mockResponse = new UrlResponse("DEMO","https://google.com");
        Mockito.when(urlService.addNew(any(UrlRequest.class))).thenReturn(mockResponse);

        mockMvc.perform(post("/api/v1/url")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.shortCode").value("DEMO"));
    }

    // Invalid request hitting the POST endpoint
    @Test
    void whenPostInvalidUrl() throws Exception {
        UrlRequest request = new UrlRequest("htp//google.com");

        mockMvc.perform(post("/api/v1/url")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());

    }

    // Valid request hitting the GET endpoint for redirection
    @Test
    void whenGetValidShortCode() throws Exception {
        String ogUrl = "https://github.com";
        String shortCode = "github";
        Mockito.when(urlService.getOriginalUrlAndIncrementCount(shortCode)).thenReturn(ogUrl);

        mockMvc.perform(get("/api/v1/url/{shortCode}",shortCode))
                .andExpect(status().isFound())
                .andExpect(header().string("Location",ogUrl));
    }

    //Invalid request hitting the GET endpoint for redirection
    @Test
    void whenGetInvalidShortCode() throws Exception {
        Mockito.when(urlService.getOriginalUrlAndIncrementCount(anyString())).thenThrow(new EntityNotFoundException("Shortcode not found"));

        mockMvc.perform(get("/api/v1/url/{shortCode}","fakeCode"))
                .andExpect(status().isNotFound());
    }

    //Valid request hitting the GET endpoint for displaying the output
    @Test
    void whenGetValidRequest() throws Exception {
        List<UrlResponse> mockResponse = new ArrayList<>();
        mockResponse.add(new UrlResponse("hi","DEMO"));
        Mockito.when(urlService.displayOutput()).thenReturn(mockResponse);

        mockMvc.perform(get("/api/v1/url"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].shortCode").value("hi"))
                .andExpect(jsonPath("$[0].originalUrl").value("DEMO"));
    }

    //When there is no output to be given for the GET endpoint for display
    @Test
    void whenGetValidRequestWithNoOutput() throws Exception {
        Mockito.when(urlService.displayOutput()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/url"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}
