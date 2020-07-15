package br.edu.ufcg.embedded.sam.controllers;

import br.edu.ufcg.embedded.sam.exceptions.ValidationError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

/**
 * Generic validation controller.
 */
@ControllerAdvice
@CrossOrigin
public class ValidationCtrl {

    private HttpHeaders getJsonUtf8Header() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8.toString());
        return headers;
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ValidationError> handleValidationException(ConstraintViolationException e) {
        ValidationError error = new ValidationError(e);
        return new ResponseEntity<>(error, getJsonUtf8Header(), HttpStatus.NOT_ACCEPTABLE);
    }
}
