package com.todo.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Collections;

public class DbConfig {

    private static final int MAX_TOTAL = 20;
    private static final String INIT_SQL = "select 1;";
    //TODO understand "all" connection pool options
    public static DataSource dataSource(String url, String login, String password){
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(url);
        ds.setUsername(login);
        ds.setPassword(password);
        ds.setMaxTotal(MAX_TOTAL);
        ds.setConnectionInitSqls(Collections.singletonList(INIT_SQL));
        return ds;
    }

    public static JdbcTemplate jdbcTemplate (String url, String login, String password){
        return new JdbcTemplate(dataSource(url, login, password));
    }

    public static DSLContext jooq(String url, String login, String password){
        DataSource ds = dataSource(url, login, password);
        DSLContext dsl = DSL.using(ds, SQLDialect.POSTGRES_9_5);
        return dsl;
    }

}