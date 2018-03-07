package com.todo.user.hibernate;

import com.todo.taskUser.TaskUserHb;
import task.hibernate.TaskHb;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "USERS")
public class UserHb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column (name = "name")
    public String name;

    @Column(name = "login")
    public String login;

    @Column(name = "password")
    public String password;

    @Column(name = "mail")
    public String mail;

    @ManyToMany (cascade = ALL, fetch = LAZY)
    @JoinTable(
            name = "user_task",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "task_id")})
    public List<TaskHb> tasks;

    @Override
    public String toString() {
        return "UserHb{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}
