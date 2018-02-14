package com.todo.todo;

import com.todo.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TaskDaoJdbcTemp {

    public static final String INSERT = "INSERT INTO tasks (name) VALUES (?)";
    public static final String MARK_COMPLETED = "UPDATE tasks SET completed_by = ?, " +
            "completed_at=now() WHERE id = ?";
    public static final String REMOVE_COMPLETED = "DELETE FROM tasks WHERE completed_by IS NOT NULL";

    private final JdbcTemplate jdbcTemplate;

    public TaskDaoJdbcTemp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save3(String name) {
        jdbcTemplate.update(INSERT, new Mapper(), name);
    }

    public void markAsDone3(Long taskId, Long userId) {
        jdbcTemplate.update(MARK_COMPLETED, userId, taskId);
    }

    public void removeCompleted3() {
        jdbcTemplate.update(REMOVE_COMPLETED);
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