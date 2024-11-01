package com.adepuu.montrack.infrastructure.users.controller;

import com.adepuu.montrack.common.response.ApiResponse;
import com.adepuu.montrack.infrastructure.users.dto.BulkCreateUserRequestDTO;
import com.adepuu.montrack.infrastructure.users.dto.CreateUserRequestDTO;
import com.adepuu.montrack.usecase.user.CreateUserUsecase;
import com.adepuu.montrack.usecase.user.GetUsersUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UsersPublicController {
  private final GetUsersUseCase getUsersUseCase;
  private final CreateUserUsecase createUserUsecase;

  public UsersPublicController(final GetUsersUseCase getUsersUseCase, CreateUserUsecase createUserUsecase) {
    this.getUsersUseCase = getUsersUseCase;
    this.createUserUsecase = createUserUsecase;
  }

  @GetMapping
  public ResponseEntity<?> getUsers() {
    return ApiResponse.successfulResponse("Get all users success", getUsersUseCase.getAllUsers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getUser(@PathVariable final Long id) {
    return ApiResponse.successfulResponse("Get user details success", getUsersUseCase.getUserById(id));
  }

  @PostMapping
  public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDTO req) {
    return ApiResponse.successfulResponse("Create new user success", createUserUsecase.createUser(req));
  }

  @PostMapping("/bulk")
  public ResponseEntity<?> createUserBulk(@RequestBody BulkCreateUserRequestDTO req) {
    return ApiResponse.successfulResponse("Create new user success", createUserUsecase.bulkCreateUser(req));
  }
}
