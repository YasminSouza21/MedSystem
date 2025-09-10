package com.yasmin.projects.medsystem.api.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<FieldErrors>> validationException(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest().body(e.getFieldErrors().stream()
                .map(fieldError -> new FieldErrors(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Error> entityNotFoundException(EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now()));
    }

    public record FieldErrors(
            String field,
            String message
    ){
    }

    public record Error(
            String message,
            Integer status,
            LocalDateTime timestamp
    ){}
}
