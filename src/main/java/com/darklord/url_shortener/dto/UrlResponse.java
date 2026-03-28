package com.darklord.url_shortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema
public class UrlResponse {
    
    private String shortCode;
    private String originalUrl;
    
}
