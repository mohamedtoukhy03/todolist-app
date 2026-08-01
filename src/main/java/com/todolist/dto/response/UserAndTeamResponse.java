package com.todolist.dto.response;

import com.todolist.entity.enums.TeamRole;
import com.todolist.entity.id.UserTeamId;

public class UserAndTeamResponse {
    private UserTeamId userTeamId;
    private TeamRole teamRole;

    public UserTeamId getUserTeamId() {
        return userTeamId;
    }

    public void setUserTeamId(UserTeamId userTeamId) {
        this.userTeamId = userTeamId;
    }

    public TeamRole getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(TeamRole teamRole) {
        this.teamRole = teamRole;
    }
}
