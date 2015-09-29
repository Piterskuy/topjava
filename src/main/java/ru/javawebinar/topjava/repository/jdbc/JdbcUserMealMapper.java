package ru.javawebinar.topjava.repository.jdbc;

/**
 * Created by Piterskuy
 * on 29.09.2015.
 */

import java.sql.ResultSet;
        import java.sql.SQLException;
        import org.springframework.jdbc.core.RowMapper;
import ru.javawebinar.topjava.model.UserMeal;

public class JdbcUserMealMapper implements RowMapper<UserMeal>{
        public UserMeal mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserMeal userMeal = new UserMeal();
            userMeal.setId(rs.getInt("id"));
            userMeal.setDateTime(rs.getTimestamp("dateTime").toLocalDateTime());
            userMeal.setDescription(rs.getString("description"));
            userMeal.setCalories(rs.getInt("calories"));
            return userMeal;
        }
}