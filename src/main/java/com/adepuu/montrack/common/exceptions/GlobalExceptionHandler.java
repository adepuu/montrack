package com.adepuu.montrack.common.exceptions;

import com.adepuu.montrack.common.response.ApiResponse;
import lombok.extern.java.Log;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@Log
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

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex) {
    return ApiResponse.failedResponse(HttpStatus.UNAUTHORIZED.value(), "Access denied");
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleGeneralException(Exception ex) {
    return ApiResponse.failedResponse(ex.getMessage());
  }
}
