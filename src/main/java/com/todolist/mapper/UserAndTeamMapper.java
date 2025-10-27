package com.todolist.mapper;

import com.todolist.dao.TeamDAO;
import com.todolist.dao.UserDAO;
import com.todolist.dto.request.UserAndTeamRequest;
import com.todolist.dto.response.UserAndTeamResponse;
import com.todolist.entity.UserAndTeam;
import com.todolist.service.TeamService;
import com.todolist.service.UserAndTeamService;
import com.todolist.service.UserService;
import org.springframework.stereotype.Component;


@Component
public class UserAndTeamMapper {

   UserDAO userDAO;
   TeamDAO teamDAO;

    public UserAndTeamMapper(UserDAO userDAO, TeamDAO teamDAO) {
        this.userDAO = userDAO;
        this.teamDAO = teamDAO;
    }

    public UserAndTeamResponse toDTO(UserAndTeam userAndTeam) {
        UserAndTeamResponse userAndTeamResponse = new UserAndTeamResponse();
        userAndTeamResponse.setUserTeamId(userAndTeam.getId());
        return userAndTeamResponse;
    }

    public UserAndTeam toEntity(UserAndTeamRequest userAndTeamRequest) {
        UserAndTeam userAndTeam = new UserAndTeam();
        userAndTeam.setUser(userDAO.findUserById(userAndTeamRequest.getUserId()));
        userAndTeam.setTeam(teamDAO.findTeamById(userAndTeamRequest.getTeamId()));
        return userAndTeam;
    }
}
