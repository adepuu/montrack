package com.adepuu.montrack.usecase.user.impl;

import com.adepuu.montrack.common.exceptions.DataNotFoundException;
import com.adepuu.montrack.entity.User;
import com.adepuu.montrack.infrastructure.users.dto.UserDetailResponseDTO;
import com.adepuu.montrack.infrastructure.users.repository.UsersRepository;
import com.adepuu.montrack.usecase.user.GetUsersUseCase;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserUseCaseImpl implements GetUsersUseCase {
  private final UsersRepository usersRepository;

  public GetUserUseCaseImpl(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  @Override
  public List<User> getAllUsers() {
    return usersRepository.findAll();
  }

  @Override
  @Cacheable(value = "userDetailResponseDTO", key = "#id", unless = "#result.isOnboardingFinished == true")
  public UserDetailResponseDTO getUserById(Long id) {
    var foundUser = usersRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User not found"));
    return new UserDetailResponseDTO(foundUser.getId(), foundUser.getEmail(), foundUser.getProfilePictureUrl(), foundUser.getIsOnboardingFinished());
  }
}
