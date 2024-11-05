package com.adepuu.montrack.usecase.user.impl;

import com.adepuu.montrack.entity.User;
import com.adepuu.montrack.infrastructure.users.dto.BulkCreateUserRequestDTO;
import com.adepuu.montrack.infrastructure.users.dto.CreateUserRequestDTO;
import com.adepuu.montrack.infrastructure.users.repository.UsersRepository;
import com.adepuu.montrack.usecase.user.CreateUserUsecase;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class CreateUserUsecaseImpl implements CreateUserUsecase {
  private final UsersRepository usersRepository;
  private Validator validator;

  public CreateUserUsecaseImpl(UsersRepository usersRepository, Validator validator) {
    this.validator = validator;
    this.usersRepository = usersRepository;
  }

  @Override
  public User createUser(CreateUserRequestDTO req) {
    User newUser = req.toEntity();
    //    validateUser(newUser);
    return usersRepository.save(newUser);
  }

  @Override
  public User createUserWithEntity(User req) {
    return null;
  }

  @Override
  @Transactional
  public List<User> bulkCreateUser(BulkCreateUserRequestDTO req) {
    List<User> usersList = req.getUsers().stream().map(CreateUserRequestDTO::toEntity).toList();
    usersRepository.saveAll(usersList);
    return usersList;
  }

  private void validateUser(User user) {
    Set<ConstraintViolation<User>> violations = validator.validate(user);
    if (!violations.isEmpty()) {
      StringBuilder sb = new StringBuilder();
      for (ConstraintViolation<User> violation : violations) {
        sb.append(violation.getPropertyPath()).append(violation.getMessage()).append(", ");
      }
      throw new ConstraintViolationException(sb.toString(), violations);
    }
  }
}
