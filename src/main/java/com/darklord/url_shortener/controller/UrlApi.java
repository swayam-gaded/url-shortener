package com.darklord.url_shortener.controller;

import com.darklord.url_shortener.dto.UrlRequest;
import com.darklord.url_shortener.dto.UrlResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "URL Controller Layer")
public interface UrlApi {
    @Operation(description = "allows for creation for shortCode for the originalUrl submitted in the body of the request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid URL format")
    })
    ResponseEntity<?> addNewUrl(@RequestBody UrlRequest request);

    @Operation(description = "Made for redirecting the shortCode URL to the original URL using path parameter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirecting..."),
            @ApiResponse(responseCode = "404", description = "Short code not found")
    })
    ResponseEntity<?> redirectToOriginal(@PathVariable String shortCode);

    @Operation(description = "Used to access all the original URLs and corresponding short Codes using a basic Auth role")
    @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    List<?> display();
}
