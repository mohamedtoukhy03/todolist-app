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

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH}, mappedBy = "user")
    private List<UserAndTeam> userAndTeam;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private UserAuth userAuth;

    public void addUserAuth(UserAuth userAuth) {
        this.userAuth = userAuth;
        userAuth.setUser(this);
    }

    public void addUserAndTeam(UserAndTeam userTeam) {
        if (userAndTeam == null) {
            userAndTeam = new ArrayList<>();
        }
        userAndTeam.add(userTeam);
        userTeam.setUser(this);
    }

    public List<UserAndTeam> getUserAndTeam() {
        return userAndTeam;
    }

    public void setUserAndTeam(List<UserAndTeam> userAndTeam) {
        this.userAndTeam = userAndTeam;
    }



    public UserAuth getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(UserAuth userAuth) {
        this.userAuth = userAuth;
    }

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
