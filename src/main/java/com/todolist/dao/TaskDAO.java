package com.todolist.dao;

import com.todolist.entity.Task;
import com.todolist.entity.Team;
import com.todolist.entity.User;

import java.util.List;

public interface TaskDAO {
    // CRUD features
    public Task createTask(Task task);
    public Task findTaskById(Integer id);
    public Task updateTask(Task task);
    public void deleteTask(Integer id);
    public List<Team> findTeamByTaskId(Integer taskId);
    public List<User> findUserWithTeamTaskByTaskId(Integer taskId);
    public User findUserWithIndividualTaskByTaskId(Integer taskId);
}
