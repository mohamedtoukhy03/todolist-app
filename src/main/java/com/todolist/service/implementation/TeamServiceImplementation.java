package com.todolist.service.implementation;

import com.todolist.dao.TeamDAO;
import com.todolist.dao.UserDAO;
import com.todolist.dto.request.TeamRequest;
import com.todolist.dto.response.TeamResponse;
import com.todolist.entity.Team;
import com.todolist.entity.User;
import com.todolist.entity.UserAndTeam;
import com.todolist.mapper.TeamMapper;
import com.todolist.service.TeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class TeamServiceImplementation implements TeamService {

    TeamDAO teamDAO;
    UserDAO userDAO;
    TeamMapper teamMapper;

    public TeamServiceImplementation(TeamDAO teamDAO, TeamMapper teamMapper, UserDAO userDAO) {
        this.teamDAO = teamDAO;
        this.teamMapper = teamMapper;
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public TeamResponse createTeam(TeamRequest teamRequest) {
        Team team = teamMapper.toEntity(teamRequest);
        team = teamDAO.createTeam(team);
        List<User> users = new ArrayList<>();
        List<UserAndTeam> userAndTeam = new ArrayList<>();
        teamRequest.getUserIds().forEach(userId -> users.add(userDAO.findUserById(userId)));
        for (User user : users) {
            userAndTeam.add(new UserAndTeam(team, user));
        }
        team.setUserAndTeam(userAndTeam);
        return teamMapper.toDTO(team);
    }

    @Override
    public TeamResponse findTeamById(Integer id) {
        Team team =  teamDAO.findTeamById(id);
        return teamMapper.toDTO(team);
    }

    @Override
    @Transactional
    public TeamResponse updateTeam(Integer id, TeamRequest teamRequest) {
        Team tempTeam = teamMapper.toEntity(teamRequest);
        tempTeam.setTeamId(id);
        Team team = teamDAO.updateTeam(tempTeam);
        return teamMapper.toDTO(team);
    }

    @Override
    @Transactional
    public void deleteTeamById(Integer id) {
        teamDAO.deleteTeamById(id);
    }

    @Override
    public List<TeamResponse> findTeamsByUserId(Integer userId) {
        List<Team> teams =  teamDAO.findTeamsByUserId(userId);
        List<TeamResponse> teamResponses = new ArrayList<>();
        teams.forEach(team -> teamResponses.add(teamMapper.toDTO(team)));
        return teamResponses;
    }
}
