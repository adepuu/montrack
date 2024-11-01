package com.adepuu.montrack.usecase.user.impl;

import com.adepuu.montrack.entity.Users;
import com.adepuu.montrack.infrastructure.users.gateway.UsersDatabaseGateway;
import com.adepuu.montrack.usecase.user.GetUsersUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserUseCaseImpl implements GetUsersUseCase {
  private final UsersDatabaseGateway usersDatabaseGateway;

  public GetUserUseCaseImpl(final UsersDatabaseGateway usersDatabaseGateway) {
    this.usersDatabaseGateway = usersDatabaseGateway;
  }

  @Override
  public List<Users> getAllUsers() {
    return usersDatabaseGateway.findAll();
  }

  @Override
  public Users getUserById(Long id) {
    return usersDatabaseGateway.findById(id);
  }
}
