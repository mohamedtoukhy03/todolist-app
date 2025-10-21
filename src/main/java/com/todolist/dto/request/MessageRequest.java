package com.todolist.dto.request;

import com.todolist.entity.id.UserTeamId;

public class MessageRequest {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
