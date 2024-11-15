package com.adepuu.montrack.infrastructure.users.controller;

import com.adepuu.montrack.common.response.ApiResponse;
import com.adepuu.montrack.infrastructure.security.Claims;
import com.adepuu.montrack.infrastructure.users.dto.LoginRequestDTO;
import com.adepuu.montrack.usecase.auth.LoginUsecase;
import com.adepuu.montrack.usecase.auth.TokenBlacklistUsecase;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  private final LoginUsecase loginUsecase;

  private final TokenBlacklistUsecase TokenBlacklistUsecase;

  public AuthController(LoginUsecase loginUsecase,
      com.adepuu.montrack.usecase.auth.TokenBlacklistUsecase tokenBlacklistUsecase) {
    this.loginUsecase = loginUsecase;

    TokenBlacklistUsecase = tokenBlacklistUsecase;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@Validated @RequestBody LoginRequestDTO req) {
    return ApiResponse.successfulResponse("Login successful", loginUsecase.authenticateUser(req));
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout() {
    String token = Claims.getJwtTokeString();
    String expiredAt = Claims.getJwtExpirationDate();
    TokenBlacklistUsecase.blacklistToken(token, expiredAt); // Adjust duration as needed
    return ApiResponse.successfulResponse("Logout successful", null);
  }
}
