package com.adepuu.montrack.infrastructure.users.repository;

import com.adepuu.montrack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmailAndPassword(String email, String password);
  Optional<User> findByEmailContainingIgnoreCase(String email);
//  select * from user where lower(email) like concat(%lower(:email)%) ?
}
