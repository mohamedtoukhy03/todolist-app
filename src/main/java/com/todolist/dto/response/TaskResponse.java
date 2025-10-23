package com.todolist.dto.response;

import com.todolist.entity.id.UserTeamId;

import java.util.List;

public class TaskResponse {
    private Integer taskId;
    private String taskName;
    private String taskNote;
    private List<UserTeamId> userTeamIds;

    public List<UserTeamId> getUserTeamIds() {
        return userTeamIds;
    }

    public void setUserTeamIds(List<UserTeamId> userTeamIds) {
        this.userTeamIds = userTeamIds;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskNote() {
        return taskNote;
    }

    public void setTaskNote(String taskNote) {
        this.taskNote = taskNote;
    }
}
