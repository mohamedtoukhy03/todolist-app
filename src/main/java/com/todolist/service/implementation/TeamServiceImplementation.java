package com.todolist.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.dao.TeamDAO;
import com.todolist.dto.request.TeamRequest;
import com.todolist.dto.response.TeamResponse;
import com.todolist.entity.Message;
import com.todolist.entity.Task;
import com.todolist.entity.Team;
import com.todolist.entity.User;
import com.todolist.mapper.TeamMapper;
import com.todolist.service.TeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@Service
public class TeamServiceImplementation implements TeamService {

    TeamDAO teamDAO;
    ObjectMapper objectMapper;
    TeamMapper teamMapper;

    public TeamServiceImplementation(TeamDAO teamDAO, ObjectMapper objectMapper,  TeamMapper teamMapper) {
        this.teamDAO = teamDAO;
        this.objectMapper = objectMapper;
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
    public TeamResponse updateTeam(TeamRequest teamRequest) {
        Team team = teamMapper.toEntity(teamRequest);
        team = teamDAO.updateTeam(team);
        return teamMapper.toDTO(team);
    }

    @Override
    public TeamResponse applyTeam(Integer id, Map<String, Object> map) {
        Team team = teamDAO.findTeamById(id);
        try {
            String json = objectMapper.writeValueAsString(map);
            objectMapper.readerForUpdating(team).readValue(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        team = teamDAO.updateTeam(team);
        return teamMapper.toDTO(team);
    }

    @Override
    @Transactional
    public void deleteTeamById(Integer id) {
        teamDAO.deleteTeamById(id);
    }
}
