package com.adepuu.montrack.usecase.auth.impl;

import com.adepuu.montrack.common.exceptions.DataNotFoundException;
import com.adepuu.montrack.entity.User;
import com.adepuu.montrack.infrastructure.users.dto.UserAuth;
import com.adepuu.montrack.infrastructure.users.repository.UsersRepository;
import com.adepuu.montrack.usecase.auth.GetUserAuthDetailsUsecase;
import lombok.extern.java.Log;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log
@Service
public class GetUserAuthDetailsUsecaseImpl implements GetUserAuthDetailsUsecase {
  private final UsersRepository usersRepository;

  public GetUserAuthDetailsUsecaseImpl(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User existingUser = usersRepository.findByEmailContainsIgnoreCase(username).orElseThrow(() -> new DataNotFoundException("User not found with email: " + username));
    log.info(existingUser.getEmail());
    log.info(existingUser.getPassword());
    UserAuth userAuth = new UserAuth();
    userAuth.setUser(existingUser);
    return userAuth;
  }
}
