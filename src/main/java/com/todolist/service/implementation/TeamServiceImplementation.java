package com.todolist.service.implementation;

import com.todolist.dao.TeamDAO;
import com.todolist.dto.request.TeamRequest;
import com.todolist.dto.response.TeamResponse;
import com.todolist.entity.Team;
import com.todolist.mapper.TeamMapper;
import com.todolist.service.TeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class TeamServiceImplementation implements TeamService {

    TeamDAO teamDAO;
    TeamMapper teamMapper;

    public TeamServiceImplementation(TeamDAO teamDAO, TeamMapper teamMapper) {
        this.teamDAO = teamDAO;
        this.teamMapper = teamMapper;
    }

    @Override
    @Transactional
    public TeamResponse createTeam(TeamRequest teamRequest) {
        Team team = teamMapper.toEntity(teamRequest);
        team = teamDAO.createTeam(team);
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
        teamDAO.findTeamById(id);
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
