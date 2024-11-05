package com.adepuu.montrack.usecase.user;

import com.adepuu.montrack.entity.User;
import com.adepuu.montrack.infrastructure.users.dto.BulkCreateUserRequestDTO;
import com.adepuu.montrack.infrastructure.users.dto.CreateUserRequestDTO;

import java.util.List;

public interface CreateUserUsecase {
  User createUser(CreateUserRequestDTO req);
  User createUserWithEntity(User req);
  List<User> bulkCreateUser(BulkCreateUserRequestDTO req);
}
