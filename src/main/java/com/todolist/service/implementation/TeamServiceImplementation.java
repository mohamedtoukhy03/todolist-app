package com.todolist.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.dao.TeamDAO;
import com.todolist.entity.Message;
import com.todolist.entity.Task;
import com.todolist.entity.Team;
import com.todolist.entity.User;
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

    public TeamServiceImplementation(TeamDAO teamDAO, ObjectMapper objectMapper) {
        this.teamDAO = teamDAO;
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional
    public Team createTeam(Team team) {
        return teamDAO.createTeam(team);
    }

    @Override
    public Team findTeamById(Integer id) {
        return teamDAO.findTeamById(id);
    }

    @Override
    @Transactional
    public Team updateTeam(Team team) {
        return teamDAO.updateTeam(team);
    }

    @Override
    public Team applyTeam(Team team, Map<String, Object> map) {
        try {
            String json = objectMapper.writeValueAsString(map);
            objectMapper.readerForUpdating(team).readValue(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return team;
    }

    @Override
    @Transactional
    public void deleteTeamById(Integer id) {
        teamDAO.deleteTeamById(id);
    }

    @Override
    public List<User> findUserByTeamId(Integer id) {
        return teamDAO.findUserByTeamId(id);
    }

    @Override
    public List<Task> findTaskByTeamId(Integer id) {
        return teamDAO.findTaskByTeamId(id);
    }

    @Override
    public List<Message> findMessageByTeamId(Integer id) {
        return teamDAO.findMessageByTeamId(id);
    }

    @Override
    public List<Team> findParentOfTeam(Integer id) {
        return teamDAO.findParentOfTeam(id);
    }

    @Override
    public List<Team> findChildOfTeam(Integer id) {
        return teamDAO.findChildOfTeam(id);
    }
}
