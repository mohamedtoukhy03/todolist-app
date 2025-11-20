package com.todolist.dto.request;

import com.todolist.entity.id.UserTeamId;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class TaskRequest {
    @NotBlank(message = "task name is required.")
    private String taskName;

    @NotBlank(message = "task note is required.")
    private String taskNote;

    private List<UserTeamId> userTeamIds;

    public List<UserTeamId> getUserTeamIds() {
        return userTeamIds;
    }

    public void setUserTeamIds(List<UserTeamId> userTeamIds) {
        this.userTeamIds = userTeamIds;
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
