package com.todo.user;

import org.jooq.DSLContext;

import java.util.List;

import static com.todo.db.Tables.USERS;


public class UserDaoJooq {

    public final DSLContext dslc;

    public UserDaoJooq(DSLContext dslc) {
        this.dslc = dslc;
    }


    public User getByLogin(String login) {
        return dslc.selectFrom(USERS).where(USERS.LOGIN.eq(login)).fetchAnyInto(User.class);
    }


    public List<User> find() {
        return dslc.selectFrom(USERS).fetchInto(User.class);
    }


    public void save(User user) {
        dslc.insertInto(USERS).columns(USERS.LOGIN, USERS.PASSWORD)
                .values(user.getLogin(), user.getPassword()).execute();
    }
}

