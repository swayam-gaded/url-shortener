package com.darklord.url_shortener.dto;

import org.hibernate.validator.constraints.URL;
import jakarta.validation.constraints.NotBlank;

public class UrlRequest {
    
    @URL
    @NotBlank
    private String originalUrl;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
    
}
