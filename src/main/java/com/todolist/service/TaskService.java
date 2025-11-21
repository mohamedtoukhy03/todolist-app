package com.todolist.service;

import com.todolist.dto.request.IndividualTaskRequest;
import com.todolist.dto.request.TeamTaskRequest;
import com.todolist.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {
    public TaskResponse createTask(TeamTaskRequest taskRequest);
    public TaskResponse createTask(IndividualTaskRequest taskRequest);
    public List<TaskResponse> findTasksByTeamId(Integer teamId);
    public TaskResponse findTaskById(Integer id);
    public TaskResponse updateTask(Integer id, TeamTaskRequest taskRequest);
    public TaskResponse updateTask(Integer id, IndividualTaskRequest taskRequest);
    public void deleteTask(Integer id);
    public List<TaskResponse> findTasksByUserId(Integer userId);
    public List<TaskResponse> findTasksByTeamIdAndUserId(Integer teamId, Integer userId);
    public List<TaskResponse> findIndividualTasksByUserId(Integer userId);
}
