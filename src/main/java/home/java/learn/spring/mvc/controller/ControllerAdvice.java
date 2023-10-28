package home.java.learn.spring.mvc.controller;

import home.java.learn.spring.mvc.custom.exception.NotFoundException;
import org.springframework.http.ResponseEntity;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity handleException(Exception e) {
        return ResponseEntity.notFound().build();
    }
}
