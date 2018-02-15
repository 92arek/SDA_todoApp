package com.todo.user;

import com.todo.todo.Filter;
import com.todo.todo.Page;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.todo.db.Tables.USERS;


public class UserDaoJooq {

    private static Map<String, Field> nameToField;

    static {
        nameToField = new HashMap<>();
        nameToField.put("id", USERS.ID);
        nameToField.put("login", USERS.LOGIN);
        nameToField.put("createdAt", USERS.CREATED_AT);
    }

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

    public List<User> find2(Page page) {
        return dslc.selectFrom(USERS)
                .offset(page.getOffset())
                .limit(page.getSize())
                .fetchInto(User.class);
    }

    //filter
    public List<User> find3(Page page, List<Filter> filters) {
        List<Condition> where = new ArrayList<>();
        //  filters.stream().map(f-> nameToField.get(f.getField()).eq(f.getValue()));
        for (Filter f : filters) {
            Field field = nameToField.get(f.getField());
            Condition c = field.eq(f.getValue());
            where.add(c);
        }
        return dslc.selectFrom(USERS)
                .where(where)
                .offset(page.getOffset())
                .limit(page.getSize())
                .fetchInto(User.class);
    }

    public void save(User user) {
        //v1
        dslc.newRecord(USERS, user).store();

        //v2
        dslc.insertInto(USERS).columns(USERS.LOGIN, USERS.PASSWORD)
                .values(user.getLogin(), user.getPassword()).execute();
    }

    private Condition getCondition (Verb verb, String name){
        nameToField.get(name);
        return null;
    }
}