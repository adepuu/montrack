package com.adepuu.montrack.infrastructure.users.gateway;

import com.adepuu.montrack.entity.Users;
import com.adepuu.montrack.infrastructure.users.mapper.UserRowMapper;
import com.adepuu.montrack.infrastructure.users.repository.UsersRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UsersDatabaseGateway implements UsersRepository {
  private final JdbcTemplate jdbcTemplate;

  public UsersDatabaseGateway(final JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Users save(Users entity) {
    String sql = "INSERT INTO users (email, password, pin, profile_picture_url) VALUES (?, ?, ?, ?) RETURNING user_id";
    Long insertedUserID = jdbcTemplate.queryForObject(sql, Long.class, entity.getEmail(),
        entity.getPassword(),
        entity.getPin(),
        entity.getProfilePictureUrl());
    entity.setUserId(insertedUserID);
    return entity;
  }

  @Override
  public Users findById(Long id) {
    String sql = "select * from users where user_id = ?";
    return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
  }

  @Override
  public List<Users> findAll() {
    String sql = "select * from users";
    return jdbcTemplate.query(sql, new UserRowMapper());
  }

  @Override
  public void delete(Users entity) {

  }

  @Override
  public void deleteById(Long aLong) {

  }

  @Override
  public Users update(Users entity) {
    return null;
  }

  @Transactional
  public void bulkInsert(List<Users> entities) {
    String sql = "INSERT INTO users (email, password, pin, profile_picture_url) VALUES (?, ?, ?, ?)";
    jdbcTemplate.batchUpdate(sql, entities, entities.size(), ((ps, argument) -> {
      ps.setString(1, argument.getEmail());
      ps.setString(2, argument.getPassword());
      ps.setString(3, argument.getPin());
      ps.setString(4, argument.getProfilePictureUrl());
    }));
  }
}
