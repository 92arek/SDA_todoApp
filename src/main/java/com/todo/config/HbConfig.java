package com.todo.config;

import com.todo.user.hibernate.UserHb;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import task.hibernate.TaskHb;

import static org.hibernate.cfg.AvailableSettings.*;

public class HbConfig {

    public static SessionFactory sessionFactory() {
        return new Configuration()
                .addAnnotatedClass(UserHb.class)
                .addAnnotatedClass(TaskHb.class)
                .addResource("mapping/users.hbm.xml")
                .setProperty(Environment.DRIVER, "org.postgresql.Driver")
                .setProperty(URL, "jdbc:postgresql://localhost:5432/hibernate")
                .setProperty(USER, "postgres")
                .setProperty(PASS, "postgres")
                .setProperty(DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect")
                .setProperty(CURRENT_SESSION_CONTEXT_CLASS, "thread")
                .setProperty(SHOW_SQL, Boolean.TRUE.toString())
                .buildSessionFactory();
    }
}