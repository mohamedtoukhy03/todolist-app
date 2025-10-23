package com.todolist.dto.response;

import com.todolist.entity.id.UserTeamId;

import java.util.List;

public class MessageResponse {
    private Integer messageId;
    private String message;
    private UserTeamId userTeamId;


    public UserTeamId getUserTeamId() {
        return userTeamId;
    }

    public void setUserTeamId(UserTeamId userTeamId) {
        this.userTeamId = userTeamId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
