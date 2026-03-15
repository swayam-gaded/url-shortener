package com.darklord.url_shortener.repository;

import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.darklord.url_shortener.model.UrlEntity;


@Repository
public interface UrlRepo extends JpaRepository<UrlEntity,Long> {

    @Query(value = "SELECT nextval('url_sequence')", nativeQuery = true)
    Long getNextSequenceValue();

    @Modifying
    @Transactional
    @Query(value="UPDATE url_entity SET clicks = clicks + 1 WHERE short_code = :shortCode",nativeQuery = true)
    void incrementCount(String shortCode);

    Optional<UrlEntity> findByShortCode(String shortCode);
    Optional<UrlEntity> findByOriginalUrl(String originalUrl);
}
