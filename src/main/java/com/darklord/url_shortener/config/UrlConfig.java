package com.darklord.url_shortener.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.darklord.url_shortener.model.UrlShortener;
import com.darklord.url_shortener.repository.UrlRepo;


@Configuration
public class UrlConfig {

     @Bean
     CommandLineRunner commandLineRunner(UrlRepo urlRepo) {
        return args -> {
            UrlShortener first = new UrlShortener("https://github.com/swayam-gaded/url-shortener");
            UrlShortener second = new UrlShortener("https://teachyourselfcs.com/");

            urlRepo.saveAll(
                List.of(first,second)
            );
        };
     }
}
