package com.todolist.service;

import com.todolist.entity.Message;
import com.todolist.entity.Task;
import com.todolist.entity.Team;
import com.todolist.entity.User;

import java.util.List;
import java.util.Map;

public interface TeamService {
    public Team createTeam(Team team);
    public Team findTeamById(Integer id);
    public Team updateTeam(Team team);
    public Team applyTeam(Team team, Map<String, Object> map);
    public void deleteTeamById(Integer id);
    public List<User> findUserByTeamId(Integer id);
    public List<Task> findTaskByTeamId(Integer id);
    public List<Message> findMessageByTeamId(Integer id);
    public List<Team> findParentOfTeam(Integer id);
    public List<Team> findChildOfTeam(Integer id);
}
