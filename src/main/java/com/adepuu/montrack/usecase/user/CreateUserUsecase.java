package com.adepuu.montrack.usecase.user;

import com.adepuu.montrack.entity.Users;
import com.adepuu.montrack.infrastructure.users.dto.BulkCreateUserRequestDTO;
import com.adepuu.montrack.infrastructure.users.dto.CreateUserRequestDTO;

import java.util.List;

public interface CreateUserUsecase {
  Users createUser(CreateUserRequestDTO req);
  List<Users> bulkCreateUser(BulkCreateUserRequestDTO req);
}
