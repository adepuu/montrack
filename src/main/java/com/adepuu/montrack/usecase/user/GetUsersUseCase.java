package com.adepuu.montrack.usecase.user;

import com.adepuu.montrack.entity.User;

import java.util.List;

public interface GetUsersUseCase {
  List<User> getAllUsers();
  User getUserById(Long id);
}
