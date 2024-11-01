package com.adepuu.montrack.common.exceptions;

import com.adepuu.montrack.common.response.ApiResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DataAccessException.class)
  public ResponseEntity<?> handleDataAccessException(DataAccessException ex) {
    return ApiResponse.failedResponse(ex.getMessage());
  }

  @ExceptionHandler(DuplicateEmailException.class)
  public ResponseEntity<?> handleDuplicateEmailException(DuplicateEmailException ex) {
    return ApiResponse.failedResponse(ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleGeneralException(Exception ex) {
    return ApiResponse.failedResponse(ex.getMessage());
  }
}
