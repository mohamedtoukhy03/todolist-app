package com.todolist.dto.request;

import com.todolist.entity.id.UserTeamId;

public class MessageRequest {
    private String message;
    private UserTeamId userTeamId;

    public UserTeamId getUserTeamId() {
        return userTeamId;
    }

    public void setUserTeamId(UserTeamId userTeamId) {
        this.userTeamId = userTeamId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
