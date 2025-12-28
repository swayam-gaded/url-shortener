package com.darklord.url_shortener.url;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UrlRepo extends JpaRepository<UrlShortener,Long> {

    @Query("SELECT s FROM UrlShortener s WHERE s.shortCode = ?1")
    Optional<UrlShortener> findByShortCode(String shortCode);

    @Query("SELECT s FROM UrlShortener s WHERE s.originalUrl = ?1")
    Optional<UrlShortener> findByOriginalUrl(String originalUrl);
}
