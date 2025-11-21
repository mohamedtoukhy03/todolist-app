package com.todolist.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Embeddable
public class UserTeamId {
    @NotNull(message = "message is not associated to any user")
    @Column(name = "user_id")
    private Integer userId;

    @NotNull(message = "message is not associated to any team")
    @Column(name = "team_id")
    private Integer teamId;

    public UserTeamId(){}
    public UserTeamId(Integer userId, Integer teamId) {
        this.userId = userId;
        this.teamId = teamId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserTeamId that)) return false;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(teamId, that.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, teamId);
    }
}
