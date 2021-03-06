package com.todo.user;

import com.todo.config.DbConfig;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.sql.SQLException;

public class UserTestMain {

    private static final String URL = "jdbc:postgresql://localhost:5432/todo";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "postgres";

    //TODO get DB login data from ENV Variables or args or external file
    public static void main(String[] args) throws SQLException {
        //TODO change to builder pattern

        DataSource ds = new DbConfig().dataSource(URL, LOGIN, PASSWORD);
        Flyway flyway = new Flyway();
        flyway.setDataSource(ds);
        flyway.migrate();


        //Fail migration
        System.out.println("Using DataSource directly");
        System.out.println(new UserDao(ds).getByLogin("John"));

        System.out.println("Using JDBC TEMP");
        System.out.println(new UserDaoJdbcTemp(DbConfig.jdbcTemplate(URL,LOGIN,PASSWORD)).getByLogin("John"));
    }
}