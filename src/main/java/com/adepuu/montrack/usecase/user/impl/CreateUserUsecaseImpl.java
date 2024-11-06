package com.adepuu.montrack.usecase.user.impl;

import com.adepuu.montrack.entity.Role;
import com.adepuu.montrack.entity.User;
import com.adepuu.montrack.infrastructure.users.dto.BulkCreateUserRequestDTO;
import com.adepuu.montrack.infrastructure.users.dto.CreateUserRequestDTO;
import com.adepuu.montrack.infrastructure.users.repository.RoleRepository;
import com.adepuu.montrack.infrastructure.users.repository.UsersRepository;
import com.adepuu.montrack.usecase.user.CreateUserUsecase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Log
@Service
public class CreateUserUsecaseImpl implements CreateUserUsecase {
  private final UsersRepository usersRepository;
  private final PasswordEncoder passwordEncoder;
  private final RoleRepository roleRepository;

  public CreateUserUsecaseImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
    this.usersRepository = usersRepository;
    this.passwordEncoder = passwordEncoder;
    this.roleRepository = roleRepository;
  }

  @Override
  public User createUser(CreateUserRequestDTO req){
    User newUser = req.toEntity();
    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

    Optional<Role> defaultRole = roleRepository.findByName("USER");
    if (defaultRole.isPresent()) {
        newUser.getRoles().add(defaultRole.get());
    } else {
        throw new RuntimeException("Default role not found");
    }

    try {
      usersRepository.save(newUser);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return newUser;
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
}
