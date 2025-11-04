package com.todolist.service;

import com.todolist.dto.request.TeamRequest;
import com.todolist.dto.response.TeamResponse;
import com.todolist.entity.Message;
import com.todolist.entity.Task;
import com.todolist.entity.Team;
import com.todolist.entity.User;

import java.util.List;
import java.util.Map;

public interface TeamService {
    public TeamResponse createTeam(TeamRequest teamRequest);
    public TeamResponse findTeamById(Integer id);
    public TeamResponse updateTeam(Integer id, TeamRequest teamRequest);
    public void deleteTeamById(Integer id);
    public List<TeamResponse> findTeamsByUserId(Integer userId);
}
