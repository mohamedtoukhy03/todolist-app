package com.todolist.dto;

import com.todolist.entity.id.UserTeamId;

import java.util.List;

public class UserAndTeamDTO {
    private UserTeamId id;
    private Integer userId;
    private Integer teamId;
    private List<Integer> tasks;

    public UserAndTeamDTO() {}

    public UserTeamId getId() {
        return id;
    }

    public void setId(UserTeamId id) {
        this.id = id;
    }

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

    public List<Integer> getTasks() {
        return tasks;
    }

    public void setTasks(List<Integer> tasks) {
        this.tasks = tasks;
    }
}
