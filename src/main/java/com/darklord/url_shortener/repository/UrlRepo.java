package com.darklord.url_shortener.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.darklord.url_shortener.model.UrlShortener;


@Repository
public interface UrlRepo extends JpaRepository<UrlShortener,Long> {

    @Query("SELECT s FROM UrlShortener s WHERE s.shortCode = ?1")
    Optional<UrlShortener> findByShortCode(String shortCode);

    @Query("SELECT s FROM UrlShortener s WHERE s.originalUrl = ?1")
    Optional<UrlShortener> findByOriginalUrl(String originalUrl);
}
