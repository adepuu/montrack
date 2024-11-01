package com.adepuu.montrack.usecase.user.impl;

import com.adepuu.montrack.entity.Users;
import com.adepuu.montrack.infrastructure.users.dto.BulkCreateUserRequestDTO;
import com.adepuu.montrack.infrastructure.users.dto.CreateUserRequestDTO;
import com.adepuu.montrack.infrastructure.users.gateway.UsersDatabaseGateway;
import com.adepuu.montrack.usecase.user.CreateUserUsecase;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateUserUsecaseImpl implements CreateUserUsecase {
  private final UsersDatabaseGateway usersDatabaseGateway;

  public CreateUserUsecaseImpl(UsersDatabaseGateway usersDatabaseGateway) {
    this.usersDatabaseGateway = usersDatabaseGateway;
  }

  @Override
  public Users createUser(CreateUserRequestDTO req) {
    return usersDatabaseGateway.save(req.toEntity());
  }

  @Override
  public List<Users> bulkCreateUser(BulkCreateUserRequestDTO req) {
    List<Users> usersList = req.getUsers().stream().map(CreateUserRequestDTO::toEntity).toList();
    usersDatabaseGateway.bulkInsert(usersList);
    return usersList;
  }
}
