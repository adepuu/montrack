package com.adepuu.montrack.usecase.user;

import com.adepuu.montrack.entity.User;
import com.adepuu.montrack.infrastructure.users.dto.UserDetailResponseDTO;

import java.util.List;

public interface GetUsersUseCase {
  List<User> getAllUsers();
  UserDetailResponseDTO getUserById(Long id);
}
