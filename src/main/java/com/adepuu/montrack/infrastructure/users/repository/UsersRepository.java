package com.adepuu.montrack.infrastructure.users.repository;

import com.adepuu.montrack.entity.Users;
import com.adepuu.montrack.repository.GenericRepository;

import java.util.List;

public interface UsersRepository extends GenericRepository<Users, Long> {
  public void bulkInsert(List<Users> entities);
}
