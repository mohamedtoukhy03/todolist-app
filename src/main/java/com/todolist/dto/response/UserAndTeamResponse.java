package com.todolist.dto.response;

import com.todolist.entity.id.UserTeamId;

public class UserAndTeamResponse {
    private UserTeamId userTeamId;

    public UserTeamId getUserTeamId() {
        return userTeamId;
    }

    public void setUserTeamId(UserTeamId userTeamId) {
        this.userTeamId = userTeamId;
    }
}
