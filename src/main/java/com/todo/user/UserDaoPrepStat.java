package com.todo.user;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoPrepStat {

    public static final String BY_LOGIN = "select * from users where login = ?";
    public static final String FIND_ALL = "select * from users";
    public static final String SAVE = "insert into users(login, password) values(?, ?)";
    private DataSource ds;
    private UserMapper mapper;

    public UserDaoPrepStat(DataSource ds) {
        this.ds = ds;
        this.mapper = new UserMapper();
    }

    public User getBylogin2 (String login) throws SQLException {
        PreparedStatement ps = ds.getConnection().prepareStatement(BY_LOGIN);
        ps.setString(1, login);
        ResultSet rs = ps.executeQuery();
        return mapper.getSingle(rs);
    }

    public List<User> find2() throws SQLException{
        PreparedStatement ps2 = ds.getConnection().prepareStatement(FIND_ALL);
        ResultSet rs2 = ps2.executeQuery();
        return mapper.getList(rs2);
    }

    public void save2(String login, String password) throws SQLException{
        PreparedStatement ps3 = ds.getConnection().prepareStatement(SAVE);
        ps3.setString(1, login);
        ps3.setString(2, password);
        ps3.executeUpdate();
    }
}
