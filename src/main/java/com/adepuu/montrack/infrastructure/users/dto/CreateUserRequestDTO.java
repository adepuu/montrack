package com.adepuu.montrack.infrastructure.users.dto;

import com.adepuu.montrack.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDTO {
  private String email;
  private String password;
  private String pin;
  private String profilePictureUrl;

  public User toEntity() {
    User user = new User();
    user.setEmail(email);
    user.setPassword(password);
    user.setPin(pin);
    user.setProfilePictureUrl(profilePictureUrl);
    user.setIsOnboardingFinished(false);
    return user;
  }
}
