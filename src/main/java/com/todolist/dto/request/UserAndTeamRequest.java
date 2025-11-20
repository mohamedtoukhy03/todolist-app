package com.todolist.dto.request;

import jakarta.validation.constraints.NotNull;

public class UserAndTeamRequest {
    @NotNull
    private Integer userId;

    @NotNull
    private Integer teamId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}
