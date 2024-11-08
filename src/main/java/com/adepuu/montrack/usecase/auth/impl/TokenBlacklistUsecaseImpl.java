package com.adepuu.montrack.usecase.auth.impl;


import com.adepuu.montrack.infrastructure.repository.RedisTokenRepository;
import com.adepuu.montrack.usecase.auth.TokenBlacklistUsecase;

import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class TokenBlacklistUsecaseImpl implements TokenBlacklistUsecase {
    private final RedisTokenRepository redisTokenRepository;

    public TokenBlacklistUsecaseImpl(RedisTokenRepository redisTokenRepository) {
        this.redisTokenRepository = redisTokenRepository;
    }

    @Override
    public void blacklistToken(String token, String expiredAt) {
        Duration duration = Duration.between(java.time.Instant.now(), java.time.Instant.parse(expiredAt));
        redisTokenRepository.saveToken(token, duration);
    }

    @Override
    public boolean isTokenBlacklisted(String token) {
        return redisTokenRepository.isTokenBlacklisted(token);
    }
}
