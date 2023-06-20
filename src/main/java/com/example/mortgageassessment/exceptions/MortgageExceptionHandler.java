package com.example.mortgageassessment.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception Handler
 *
 * @author : Narges Rezaei
 **/
@Slf4j
@ControllerAdvice
public class MortgageExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InterestRateNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(InterestRateNotFoundException ex) {

        log.error(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

    }

}
