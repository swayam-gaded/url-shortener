package com.darklord.url_shortener.url;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    public String shortCodeGenerator() {
        StringBuilder shortUrl = new StringBuilder();
        for(int i=0;i<5;i++) {
            int random = (int)(Math.random()*3);
            switch (random) {
                case 0:
                    char randomNum = (char)(Math.random()*10+48);
                    shortUrl.append(randomNum);
                    break;
                case 1:
                    char randomCapAlpha = (char)(Math.random()*26+65);
                    shortUrl.append(randomCapAlpha);
                    break;
                case 2:
                    char randomSmallAlpha = (char)(Math.random()*26+97);
                    shortUrl.append(randomSmallAlpha);
                    break;
                default:
                    break;
            }
        }
        return shortUrl.toString();
    }

    public boolean existShortCode(String shortCode) {
        Optional<UrlShortener> existingUrl = urlRepo.findByShortCode(shortCode);
        return existingUrl.isPresent();
    }

    public void addNew(UrlShortener urlShortener) {
        Optional<UrlShortener> urlOptional = urlRepo.findByOriginalUrl(urlShortener.getOriginalUrl());
        if(urlOptional.isPresent()) {
            throw new IllegalStateException("Shortened Url Exists Already");
        }
        String shortCode = shortCodeGenerator();
        while (existShortCode(shortCode)) {
            shortCode = shortCodeGenerator();
        }
        urlShortener.shortCode = shortCode;
        urlRepo.save(urlShortener);
    }
}
