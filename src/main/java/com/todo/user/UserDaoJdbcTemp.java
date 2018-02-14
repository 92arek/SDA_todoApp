package com.todo.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class UserDaoJdbcTemp {
    public static final String BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    public static final String FIND_ALL = "SELECT * FROM users";
    public static final String SAVE = "INSERT INTO users(login, password) VALUES(?, ?)";

    private final JdbcTemplate jdbcTemplate;

    public UserDaoJdbcTemp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getByLogin(String login) {
        return jdbcTemplate.queryForObject(BY_LOGIN, new Mapper(), login);
    }

    public List<User> find() {
        return jdbcTemplate.query(FIND_ALL, new Mapper());
    }

    public void save(String login, String password) {
        jdbcTemplate.update(SAVE, login, password);
    }
}

class Mapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getInt("id"),
                rs.getString("login"),
                rs.getString("password"));
    }
}
