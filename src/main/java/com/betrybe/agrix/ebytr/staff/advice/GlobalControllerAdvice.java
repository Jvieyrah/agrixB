package com.betrybe.agrix.ebytr.staff.advice;

import com.betrybe.agrix.ebytr.staff.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type Global controller advice.
 */
@ControllerAdvice
public class GlobalControllerAdvice {

  /**
   * Handle not found exception response entity.
   */
  @ExceptionHandler
  public ResponseEntity<String> handleNotFoundException(NotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

}
