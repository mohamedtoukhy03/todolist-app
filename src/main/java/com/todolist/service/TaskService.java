package com.todolist.service;

import com.todolist.dto.request.TaskRequest;
import com.todolist.dto.response.TaskResponse;
import com.todolist.dto.response.TeamResponse;
import com.todolist.dto.response.UserResponse;
import com.todolist.entity.Task;
import com.todolist.entity.Team;
import com.todolist.entity.User;

import java.util.List;

public interface TaskService {
    public TaskResponse createTask(TaskRequest taskRequest);
    public List<TaskResponse> findTasksByTeamId(Integer teamId);
    public TaskResponse findTaskById(Integer id);
    public TaskResponse updateTask(TaskRequest taskRequest);
    public void deleteTask(Integer id);
    public List<TaskResponse> findTasksByUserId(Integer userId);
    public List<TaskResponse> findTasksByTeamIdAndUserId(Integer teamId, Integer userId);
    public List<TaskResponse> findIndividualTasksByUserId(Integer userId);
}
