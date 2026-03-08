package com.darklord.url_shortener.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import com.darklord.url_shortener.repository.UrlRepo;
import com.darklord.url_shortener.dto.UrlRequest;
import com.darklord.url_shortener.dto.UrlResponse;
import com.darklord.url_shortener.model.UrlShortener;
import com.darklord.url_shortener.util.Base62Util;


@Service
public class UrlService {

    private final UrlRepo urlRepo;

    @Autowired
    public UrlService(UrlRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    public String getOriginalUrl(String shortCode) {
        return urlRepo.findByShortCode(shortCode)
            .map(UrlShortener::getOriginalUrl)
            .orElseThrow(() -> new EntityNotFoundException("URL not found for code: " + shortCode));
    }

    @Transactional
    public UrlResponse addNew(UrlRequest request) {
        urlRepo.findByOriginalUrl(request.getOriginalUrl())
           .ifPresent(u -> { 
            throw new IllegalStateException("The shortURL for your given URL exists already"); 
        });

        UrlShortener url = new UrlShortener();
        url.setOriginalUrl(request.getOriginalUrl());
        
        if(request.getCustomAlias() != null && !request.getCustomAlias().isBlank()) {

            if(urlRepo.findByShortCode(request.getCustomAlias()).isPresent()) {
                throw new IllegalStateException("Custom alias already exists !! ");
            }

            url.setShortCode(request.getCustomAlias());
            urlRepo.save(url);
        }
        else {
            urlRepo.save(url);
            String shortCode = Base62Util.encode(url.getId());
            url.setShortCode(shortCode);
            urlRepo.save(url);
        }
        return new UrlResponse(url.getShortCode(), url.getOriginalUrl());

    }

    public List<UrlResponse> displayOutput() {
        return urlRepo.findAll()
            .stream()
            .map(url -> new UrlResponse(url.getShortCode(), url.getOriginalUrl()))
            .toList();
    }
}
