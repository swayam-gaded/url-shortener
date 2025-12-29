package com.darklord.url_shortener.url;


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
    private String shortCode;
    private String originalUrl;

    public UrlShortener(String originalUrl) {
        this.originalUrl = originalUrl;
    }
    
    public UrlShortener() {
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

    @Override
    public String toString() {
        return  "UrlShortener {" +
                "id: "+id +
                "shortCode: "+shortCode +
                "originalUrl: "+originalUrl+
                "}";
    }
}
