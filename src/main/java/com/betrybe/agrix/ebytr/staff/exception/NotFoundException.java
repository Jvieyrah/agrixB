package com.betrybe.agrix.ebytr.staff.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Farm not found exception.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException  {

  /**
   * Instantiates a new Farm not found exception.
   */
  public NotFoundException(String message) {
    super(message);
  }

}
