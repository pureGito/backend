package puregito.backend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExistByException.class)
    public ResponseEntity<?> handleExistByException(ExistByException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
