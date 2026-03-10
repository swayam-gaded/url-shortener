package com.darklord.url_shortener.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class UrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String shortCode;

    @Column(nullable = false)
    private String originalUrl;

    private LocalDateTime createdAt;

    public UrlEntity(String originalUrl) {
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
