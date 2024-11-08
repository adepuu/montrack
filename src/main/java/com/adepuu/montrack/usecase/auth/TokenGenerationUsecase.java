package com.adepuu.montrack.usecase.auth;

import org.springframework.security.core.Authentication;

public interface TokenGenerationUsecase {
  String generateToken(Authentication authentication);
}
