package com.adepuu.montrack.infrastructure.users.dto;

import com.adepuu.montrack.entity.Users;
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

  public Users toEntity() {
    Users user = new Users();
    user.setEmail(email);
    user.setPassword(password);
    user.setPin(pin);
    user.setProfilePictureUrl(profilePictureUrl);
    return user;
  }
}
