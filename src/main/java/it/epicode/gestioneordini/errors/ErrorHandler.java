package it.epicode.gestioneordini.errors;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException error){
        String strError = error.getMessage();
        return new ResponseEntity<>(strError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<String> handleEntityExists(EntityExistsException error){
        String strError = error.getMessage();
        return new ResponseEntity<>(strError, HttpStatus.FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException error){
        Map errorResponse = new HashMap();
        error.getBindingResult().getAllErrors().forEach(
                er->{
                    FieldError fError = (FieldError) er;
                    String nomeCampo = fError.getField();
                    String errorMessage = er.getDefaultMessage();
                    errorResponse.put(nomeCampo, errorMessage);
                }
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
