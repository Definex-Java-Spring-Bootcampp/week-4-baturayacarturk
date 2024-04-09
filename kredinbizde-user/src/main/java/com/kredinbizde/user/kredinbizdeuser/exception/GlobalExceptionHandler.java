package com.kredinbizde.user.kredinbizdeuser.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private HttpServletRequest request;
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BusinessProblemDetails> handleBusinessException(BusinessException ex) {
        BusinessProblemDetails problemDetails = new BusinessProblemDetails();
        problemDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        problemDetails.setType(request.getRequestURI().toString());
        problemDetails.setTitle("Business exception");
        problemDetails.setDetail(ex.getMessage());
        problemDetails.setInstance("");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetails);
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ValidationProblemDetails> handleValidationException(ValidationException ex) {
        ValidationProblemDetails problemDetails = new ValidationProblemDetails();
        problemDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        problemDetails.setType(request.getRequestURI().toString());
        problemDetails.setTitle(ex.getMessage());
        problemDetails.setDetail(ex.getErrors());
        problemDetails.setInstance("");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetails);
    }
}
