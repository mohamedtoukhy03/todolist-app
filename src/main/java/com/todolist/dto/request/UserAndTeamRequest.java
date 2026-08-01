package com.todolist.dto.request;

import com.todolist.entity.enums.TeamRole;
import jakarta.validation.constraints.NotNull;

public class UserAndTeamRequest {
    @NotNull
    private Integer userId;

    @NotNull
    private Integer teamId;

    private TeamRole teamRole = TeamRole.TEAM_MEMBER;

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

    public TeamRole getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(TeamRole teamRole) {
        this.teamRole = teamRole;
    }
}
