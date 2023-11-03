package home.java.learn.spring.mvc.controller;

import home.java.learn.spring.mvc.custom.exception.NotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity handleException(Exception e) {
        return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body("Requested book does not exist ");
    }

    @ExceptionHandler(value={MethodArgumentNotValidException.class})
    public ResponseEntity handleNoFoundException(MethodArgumentNotValidException e) {
        List<Map<String, String>> collect;
        collect = e.getFieldErrors().stream().map(fieldError -> {
            Map<String, String> errors = new HashMap<String, String>();
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            return errors;
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(collect);
    }
}
