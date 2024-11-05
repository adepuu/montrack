package com.adepuu.montrack.usecase.auth.impl;

import com.adepuu.montrack.common.exceptions.DataNotFoundException;
import com.adepuu.montrack.entity.User;
import com.adepuu.montrack.infrastructure.users.dto.LoginRequestDTO;
import com.adepuu.montrack.infrastructure.users.repository.UsersRepository;
import com.adepuu.montrack.usecase.auth.LoginUsecase;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUsecaseImpl implements LoginUsecase {
  private final UsersRepository usersRepository;

  public LoginUsecaseImpl(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  @Override
  public User authenticateUser(LoginRequestDTO req) {
    Optional<User> user = usersRepository.findByEmailAndPassword(req.getEmail(), req.getPassword());
    if (user.isEmpty()) {
      throw new DataNotFoundException("Wrong credentials");
    }
    return user.get();
  }
}
