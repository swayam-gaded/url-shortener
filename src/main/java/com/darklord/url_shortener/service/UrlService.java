package com.darklord.url_shortener.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import com.darklord.url_shortener.repository.UrlRepo;
import com.darklord.url_shortener.dto.UrlRequest;
import com.darklord.url_shortener.dto.UrlResponse;
import com.darklord.url_shortener.model.UrlEntity;
import com.darklord.url_shortener.util.HashidUtil;


@Service
public class UrlService {

    private final UrlRepo urlRepo;
    private final HashidUtil hashidUtil;

    @Autowired
    public UrlService(UrlRepo urlRepo, HashidUtil hashidUtil) {
        this.urlRepo = urlRepo;
        this.hashidUtil = hashidUtil;
    }

    public String getOriginalUrl(String shortCode) {
        return urlRepo.findByShortCode(shortCode)
            .map(UrlEntity::getOriginalUrl)
            .orElseThrow(() -> new EntityNotFoundException("URL not found for code: " + shortCode));
    }

    public UrlEntity checkForDuplicateOgUrl(UrlRequest request) {
        urlRepo.findByOriginalUrl(request.getOriginalUrl())
                .ifPresent(u -> {
                    throw new IllegalStateException("The shortURL for your given URL exists already");
                });

        UrlEntity url = new UrlEntity();
        url.setOriginalUrl(request.getOriginalUrl());
        return url;
    }

    public boolean checkForCustomAlias(UrlRequest request) {
        if(request.getCustomAlias() != null && !request.getCustomAlias().isBlank()) {
            if(urlRepo.findByShortCode(request.getCustomAlias()).isPresent()) {
                throw new IllegalStateException("Custom alias already exists !! ");
            }
            else {
                return true;
            }
        }
        else { return false; }
    }

    @Transactional
    public UrlResponse addNew(UrlRequest request) {

        UrlEntity url = checkForDuplicateOgUrl(request);

        if(checkForCustomAlias(request)) {
            url.setShortCode(request.getCustomAlias());
            urlRepo.save(url);
        }
        else {
            Long nextId = urlRepo.getNextSequenceValue();
            String shortCode = hashidUtil.encode(nextId);
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
