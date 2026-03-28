package com.darklord.url_shortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@Schema
@RequiredArgsConstructor
public class UrlRequest {
    
    @URL(message="Give a valid URL")
    @NotBlank(message="You forgot to give URL")
    @NonNull
    private String originalUrl;
    @Size(min=3, max=10, message="The size must be between 3 and 10.")
    private String customAlias;

}
