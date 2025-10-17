package com.todolist.dto;

import com.todolist.entity.id.UserTeamId;

public class MessageDTO {
    private Integer id;
    private String message;
    private UserTeamId userTeamId;

    public MessageDTO() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserTeamId getUserTeamId() {
        return userTeamId;
    }

    public void setUserTeamId(UserTeamId userTeamId) {
        this.userTeamId = userTeamId;
    }
}
