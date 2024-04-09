package com.kredinbizde.application.kredinbizdeapplication.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.InternalServerErrorException;
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
    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<InternalServerExceptionDetails> handleInternalServerException(InternalServerException ex) {
        InternalServerExceptionDetails exceptionDetails = new InternalServerExceptionDetails();
        exceptionDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exceptionDetails.setType(request.getRequestURI().toString());
        exceptionDetails.setTitle("Internal Server Error");
        exceptionDetails.setDetail(ex.getMessage());
        exceptionDetails.setInstance("");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDetails);
    }
}
