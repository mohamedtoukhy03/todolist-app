package com.todolist.mapper;

import com.todolist.dto.request.UserAndTeamRequest;
import com.todolist.dto.response.UserAndTeamResponse;
import com.todolist.entity.UserAndTeam;
import com.todolist.service.TeamService;
import com.todolist.service.UserAndTeamService;
import com.todolist.service.UserService;
import org.springframework.stereotype.Component;


@Component
public class UserAndTeamMapper {

    UserService userService;
    TeamService teamService;

    public UserAndTeamMapper(UserService userService, TeamService teamService) {
        this.userService = userService;
        this.teamService = teamService;
    }

    public UserAndTeamResponse toDTO(UserAndTeam userAndTeam) {
        UserAndTeamResponse userAndTeamResponse = new UserAndTeamResponse();
        userAndTeamResponse.setUserTeamId(userAndTeam.getId());
        return userAndTeamResponse;
    }

    public UserAndTeam toEntity(UserAndTeamRequest userAndTeamRequest) {
        UserAndTeam userAndTeam = new UserAndTeam();
        userAndTeam.setUser(userService.findUserById(userAndTeamRequest.getUserId()));
        userAndTeam.setTeam(teamService.findTeamById(userAndTeamRequest.getTeamId()));
        return userAndTeam;
    }
}
