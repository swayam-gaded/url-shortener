package com.darklord.url_shortener.controller;

import com.darklord.url_shortener.dto.UrlRequest;
import com.darklord.url_shortener.dto.UrlResponse;
import com.darklord.url_shortener.service.UrlService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UrlController.class)
public class UrlControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private UrlService urlService;
    @Autowired
    private ObjectMapper objectMapper;

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
}
