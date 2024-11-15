package com.adepuu.montrack.usecase.auth;

import com.adepuu.montrack.infrastructure.users.dto.LoginRequestDTO;
import com.adepuu.montrack.infrastructure.users.dto.LoginResponseDTO;

public interface LoginUsecase {
  LoginResponseDTO authenticateUser(LoginRequestDTO req);
}