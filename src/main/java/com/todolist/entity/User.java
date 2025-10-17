package com.todolist.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "nick_name")
    private String nickName;

//
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
//    private UserAuth userAuth;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Task> tasks;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<UserAndTeam> userAndTeams;

    public User() {}

//    public User(String userName, String nickName, UserAuth userAuth) {
//        this.userName = userName;
//        this.nickName = nickName;
//        this.userAuth = userAuth;
//    }

    public User(String userName, String nickName) {
        this.userName = userName;
        this.nickName = nickName;
    }

    // sync method
//    public void addUserAuth(UserAuth userAuth) {
//        this.userAuth = userAuth;
//        userAuth.setUser(this);
//    }

    // sync method
//    public void addUserAndTeam(UserAndTeam userAndTeam) {
//        if  (this.userAndTeams == null) {
//            this.userAndTeams = new ArrayList<>();
//        }
//        this.userAndTeams.add(userAndTeam);
//        userAndTeam.setUser(this);
//    }

    // sync method
//    public void addTask(Task task){
//        if (this.tasks == null){
//            this.tasks = new ArrayList<>();
//        }
//        this.tasks.add(task);
//        task.setUser(this);
//    }

//    public UserAuth getUserAuth() {
//        return userAuth;
//    }
//
//    public void setUserAuth(UserAuth userAuth) {
//        this.userAuth = userAuth;
//    }

//    public List<Task> getTasks() {
//        return tasks;
//    }
//
//    public void setTasks(List<Task> tasks) {
//        this.tasks = tasks;
//    }

//    public List<UserAndTeam> getUserAndTeams() {
//        return userAndTeams;
//    }
//
//    public void setUserAndTeams(List<UserAndTeam> userAndTeams) {
//        this.userAndTeams = userAndTeams;
//    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
