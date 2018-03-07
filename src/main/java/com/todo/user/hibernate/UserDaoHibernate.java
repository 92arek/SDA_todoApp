package com.todo.user.hibernate;

import org.hibernate.Session;


import java.util.List;

public class UserDaoHibernate {

    private Session session;

    public UserDaoHibernate(Session session) {
        this.session = session;
    }

    public UserHb getByLogin(String login){
        return null;
    }

    public List<UserHb> find(){
        List users = session.createCriteria(UserHb.class).list();
        return users;
    }

    public void save(UserHb userHb){
        session.save(userHb);


    }

}
