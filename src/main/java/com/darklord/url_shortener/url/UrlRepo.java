package com.darklord.url_shortener.url;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepo extends JpaRepository<UrlShortener,Long> {

    @Query("SELECT s FROM Urls s WHERE s.shortCode = ?")
    Optional<UrlShortener> findByShortCode(String shortCode);
}
