package com.example.demo.exceptionHndler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class Handler {
    @ExceptionHandler(Throwable.class)
    public String exceptionHandler() {
        return "exception";
    }
}