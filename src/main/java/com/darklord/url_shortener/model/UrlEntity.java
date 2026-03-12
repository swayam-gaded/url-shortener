package com.darklord.url_shortener.model;


import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "url_gen")
    @SequenceGenerator(
            name = "url_gen",
            sequenceName = "url_sequence", // should match the SQL sequence name in the db exactly
            allocationSize = 1             // tells Hibernate to increment by 1
    )
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
