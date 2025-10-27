package com.todolist.dao;

import com.todolist.entity.Message;
import com.todolist.entity.Task;
import com.todolist.entity.Team;
import com.todolist.entity.User;

import java.util.List;

public interface TeamDAO {
    public Team createTeam(Team team);
    public Team findTeamById(Integer id);
    public Team updateTeam(Team team);
    public void deleteTeamById(Integer id);
    public List<Team> findParentOfTeam(Integer id);
    public List<Team> findChildOfTeam(Integer id);
}
