package com.darklord.url_shortener.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

import java.util.Map;
import java.time.LocalDateTime;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private ResponseEntity<Map<String,Object>> buildResponse(String message, HttpStatus status) {
        Map<String,Object> body = new HashMap<>();
        body.put("timestamp",LocalDateTime.now());
        body.put("status",status.value());
        body.put("error",status.getReasonPhrase());
        body.put("message",message);
        return new ResponseEntity<>(body,status);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String,Object>> handleIllegalState(IllegalStateException ex) {
        // Bad Request - 400 
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Even though i throw an IllegalStateException to prevent duplicate customAlias. 
    // This one is to prevent the Race Condition. 
    @ExceptionHandler(DataIntegrityViolationException.class) 
    public ResponseEntity<Map<String,Object>> handleDataIntegrity(DataIntegrityViolationException ex) {
        // Conflict - 409 
        return buildResponse("This alias is already in use by another user",HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class) 
    public ResponseEntity<Map<String,Object>> handleEntityNotFound(EntityNotFoundException ex) {
        //Not found - 404
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(),error.getDefaultMessage())
        );

        Map<String,Object> body = new HashMap<>();
        body.put("timestamp",LocalDateTime.now());
        body.put("errors",errors);
        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleAnyOtherException(Exception ex) {
        // Internal Server Error - 500
        return buildResponse("An unexpected server error occurred. Please try again later.",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
