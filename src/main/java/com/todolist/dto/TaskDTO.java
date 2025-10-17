package com.todolist.dto;

import com.todolist.entity.id.UserTeamId;

import java.util.List;

public class TaskDTO {
    private Integer id;
    private String taskName;
    private String taskNote;
    private Integer userId;
    private List<UserTeamId> useAndTeam;

    public TaskDTO() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<UserTeamId> getUseAndTeam() {
        return useAndTeam;
    }

    public void setUseAndTeam(List<UserTeamId> useAndTeam) {
        this.useAndTeam = useAndTeam;
    }
}
