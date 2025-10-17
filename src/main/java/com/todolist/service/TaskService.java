package com.todolist.service;

import com.todolist.entity.Task;
import com.todolist.entity.Team;
import com.todolist.entity.User;

import java.util.List;

public interface TaskService {
    public Task createTask(Task task);
    public Task findTaskById(Integer id);
    public Task updateTask(Task task);
    public void deleteTask(Integer id);
    public List<Team> findTeamByTaskId(Integer taskId);
    public List<User> findUserWithTeamTaskByTaskId(Integer taskId);
    public User findUserWithIndividualTaskByTaskId(Integer taskId);
}
