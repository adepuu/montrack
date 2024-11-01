package com.adepuu.montrack.usecase.user;

import com.adepuu.montrack.entity.Users;

import java.util.List;

public interface GetUsersUseCase {
  List<Users> getAllUsers();
  Users getUserById(Long id);
}
