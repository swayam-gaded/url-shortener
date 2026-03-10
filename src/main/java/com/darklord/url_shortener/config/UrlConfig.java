package com.darklord.url_shortener.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.darklord.url_shortener.model.UrlEntity;
import com.darklord.url_shortener.repository.UrlRepo;


@Configuration
public class UrlConfig {

     @Bean
     CommandLineRunner commandLineRunner(UrlRepo urlRepo) {
        return args -> {
            UrlEntity first = new UrlEntity("https://github.com/swayam-gaded/url-shortener");
            UrlEntity second = new UrlEntity("https://teachyourselfcs.com/");

            urlRepo.saveAll(
                List.of(first,second)
            );
        };
     }
}
