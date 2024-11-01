package com.adepuu.montrack.infrastructure.users.mapper;

import com.adepuu.montrack.entity.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class UserRowMapper implements RowMapper<Users> {
  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSXXX");
  @Override
  public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
    Users user = new Users();
    user.setUserId(rs.getLong("user_id"));
    user.setEmail(rs.getString("email"));
    user.setPassword(rs.getString("password"));
    user.setPin(rs.getString("pin"));
    user.setProfilePictureUrl(rs.getString("profile_picture_url"));
    user.setOnboardingFinished(rs.getBoolean("is_onboarding_finished"));


//    user.setCreatedAt(parseDate(rs.getString("created_at")));
//    user.setUpdatedAt(parseDate(rs.getString("updated_at")));
//    user.setDeletedAt(parseDate(rs.getString("deleted_at")));
    return user;
  }

  private ZonedDateTime parseDate(String date) {
    return ZonedDateTime.parse(date, formatter);
  }
}
