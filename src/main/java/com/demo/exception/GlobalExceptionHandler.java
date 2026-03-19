package com.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice //This annotation allows us to handle exceptions globally across the whole application, not just in a single controller.
public class GlobalExceptionHandler extends RuntimeException {

  @ExceptionHandler(NoteNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleNoteNotFoundException(NoteNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body(Map.of("error", ex.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class) //This annotation tells Spring that this method should be called when a MethodArgumentNotValidException is thrown, which happens when validation on an argument annotated with @Valid fails.
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) { //This method handles validation exceptions and returns a map of field errors.
      Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
              .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }
}
