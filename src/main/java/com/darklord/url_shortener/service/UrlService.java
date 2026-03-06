package com.darklord.url_shortener.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darklord.url_shortener.repository.UrlRepo;
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
        Optional<UrlShortener> urlOptional = urlRepo.findByShortCode(shortCode);
        UrlShortener url = urlOptional.get();
        return url.getOriginalUrl();
    }

    public void addNew(UrlShortener urlShortener) {
        Optional<UrlShortener> urlOptional = urlRepo.findByOriginalUrl(urlShortener.getOriginalUrl());
        if(urlOptional.isPresent()) {
            throw new IllegalStateException("Shortened Url Exists Already");
        }

        urlRepo.save(urlShortener);
        Long id = urlShortener.getId();
        String shortCode = Base62Util.encode(id);
        urlShortener.setShortCode(shortCode);
        urlRepo.save(urlShortener);
        
    }

    public List<UrlShortener> displayOutput() {
        return urlRepo.findAll();
    }
}
