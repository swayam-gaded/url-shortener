package com.darklord.url_shortener.dto;

public class UrlResponse {
    
    private String shortCode;
    private String originalUrl;
    
    public UrlResponse(String shortCode, String originalUrl) {
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }
    
}
