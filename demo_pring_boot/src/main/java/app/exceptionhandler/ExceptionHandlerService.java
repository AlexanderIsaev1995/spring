package app.exceptionhandler;

import app.exception.PersonException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExceptionHandlerService {

    @ExceptionHandler(PersonException.class)
    public String handler(PersonException ex) {
        return ex.getMessage();
    }
}
