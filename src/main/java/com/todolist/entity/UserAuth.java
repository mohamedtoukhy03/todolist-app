package com.todolist.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_auth")
public class UserAuth {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    public UserAuth() {}


    public UserAuth(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserAuth(String email, String password, String role, User user) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
