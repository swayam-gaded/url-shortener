package com.darklord.url_shortener.url;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Entity
@Table
public class UrlShortener {

    @Id
    @SequenceGenerator (
        name = "url_sequence",
        sequenceName = "url_sequence",
        allocationSize = 1
    )
    @GeneratedValue (
        strategy = GenerationType.SEQUENCE,
        generator = "url_sequence"
    )
    private Long id;
    String shortCode;
    private String originalUrl;
    private LocalDate createdAt;

    public UrlShortener(String shortCode,String originalUrl) {
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return  "UrlShortener {" +
                "id: "+id +
                "shortCode: "+shortCode +
                "originalUrl: "+originalUrl+
                "created at: "+createdAt + 
                "}";
    }
}
