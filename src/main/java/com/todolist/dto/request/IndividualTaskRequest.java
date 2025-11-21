package com.todolist.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class IndividualTaskRequest {
    @NotBlank(message = "task name is required.")
    private String taskName;

    @NotBlank(message = "task note is required.")
    private String taskNote;

    @NotNull(message = "task must be associated to at least one user")
    private Integer userId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
