package com.adepuu.montrack.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private Long userId;
    private String email;
    private String password;
    private String pin;
    private String profilePictureUrl;
    private boolean isOnboardingFinished;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private ZonedDateTime deletedAt;
}