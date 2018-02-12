package com.todo.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.lang.String.format;

public class UserDao {

    public static final String BY_LOGIN = "select * from users where login = '%s'";
    public static final String FIND_ALL = "SELECT * FROM USER";
    public static final String SAVE = "INSERT INTO USER (login, password) VALUES ('%s', '%s')";
    private Connection connection;
    private UserMapper mapper;

    public UserDao(Connection connection) {
        this.connection = connection;
        this.mapper = new UserMapper();
    }

    // Get returns single result
    // Find returns multiply results
    public User getByLogin(String login) throws SQLException {
        ResultSet res = connection.createStatement().executeQuery(
                format(BY_LOGIN, login));
        return mapper.getSingle(res);
    }

    public List<User> find() throws SQLException {
        return mapper.getList(connection.createStatement().executeQuery(FIND_ALL));
    }

    //CREATE, UPDATE, DELETE
    public void add(String login, String password) throws SQLException {
        connection.createStatement().executeUpdate(format(SAVE, login, password));
    }
}