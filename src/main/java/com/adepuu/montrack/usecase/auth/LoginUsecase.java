package com.adepuu.montrack.usecase.auth;

import com.adepuu.montrack.entity.User;
import com.adepuu.montrack.infrastructure.users.dto.LoginRequestDTO;

public interface LoginUsecase {
  User authenticateUser(LoginRequestDTO req);
}
