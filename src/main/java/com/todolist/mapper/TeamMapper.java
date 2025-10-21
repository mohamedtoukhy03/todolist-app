package com.todolist.mapper;

import com.todolist.dto.request.TeamRequest;
import com.todolist.dto.response.TeamResponse;
import com.todolist.entity.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {
    public TeamResponse toDTO(Team team) {
        TeamResponse teamResponse = new TeamResponse();
        teamResponse.setTeamName(team.getTeamName());
        teamResponse.setTeamId(team.getTeamId());
        teamResponse.setTeamDescription(team.getTeamDescription());
        return teamResponse;
    }

    public Team toEntity(TeamRequest teamRequest) {
        Team team = new Team();
        team.setTeamName(teamRequest.getTeamName());
        team.setTeamDescription(teamRequest.getTeamDescription());
        team.setTeamDescription(teamRequest.getTeamDescription());
        return team;
    }
}
