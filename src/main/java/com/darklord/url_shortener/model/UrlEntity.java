package com.darklord.url_shortener.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    private Integer clicks = 0;

    @LastModifiedDate
    private LocalDateTime lastUsedAt;

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
