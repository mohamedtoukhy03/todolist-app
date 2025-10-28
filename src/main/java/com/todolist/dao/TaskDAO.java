package com.todolist.dao;

import com.todolist.dto.response.TaskResponse;
import com.todolist.entity.Task;
import com.todolist.entity.Team;
import com.todolist.entity.User;

import java.util.List;

public interface TaskDAO {
    public Task createTask(Task task);
    public List<Task> findTasksByTeamId(Integer teamId);
    public Task findTaskById(Integer id);
    public Task updateTask(Task task);
    public void deleteTask(Integer id);
    public List<Task> findTasksByUserId(Integer userId);
    public List<Task> findTasksByTeamIdAndUserId(Integer teamId, Integer userId);
    public List<Task> findIndividualTasksByUserId(Integer userId);
}
