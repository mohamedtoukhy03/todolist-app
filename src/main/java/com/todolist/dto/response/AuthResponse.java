package com.todolist.dto.response;

public class AuthResponse {
    private String token;
    private String type = "Bearer";
    private Integer userId;
    private String email;
    private String userName;
    private String nickName;

    public AuthResponse(String token, Integer userId, String email, String userName, String nickName) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.userName = userName;
        this.nickName = nickName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
