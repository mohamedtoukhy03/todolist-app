package com.todolist.dto.request;

import com.todolist.entity.enums.TaskPriority;
import com.todolist.entity.enums.TaskStatus;
import com.todolist.entity.id.UserTeamId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.List;

public class TeamTaskRequest {
    @NotBlank(message = "task name is required.")
    private String taskName;

    @NotBlank(message = "task note is required.")
    private String taskNote;

    @NotEmpty(message = "task must be associated to at least one user.")
    private List<UserTeamId> userTeamIds;

    private TaskStatus status;
    private TaskPriority priority;
    private LocalDateTime dueDate;

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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
