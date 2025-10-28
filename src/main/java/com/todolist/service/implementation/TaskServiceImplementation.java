package com.todolist.service.implementation;

import com.todolist.dao.TaskDAO;
import com.todolist.dto.request.TaskRequest;
import com.todolist.dto.response.TaskResponse;
import com.todolist.dto.response.TeamResponse;
import com.todolist.dto.response.UserResponse;
import com.todolist.entity.Task;
import com.todolist.entity.Team;
import com.todolist.entity.User;
import com.todolist.entity.UserAuth;
import com.todolist.mapper.TaskMapper;
import com.todolist.mapper.TeamMapper;
import com.todolist.mapper.UserMapper;
import com.todolist.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImplementation implements TaskService {

    TaskDAO taskDAO;
    TaskMapper taskMapper;

    public TaskServiceImplementation(TaskDAO taskDAO, TaskMapper taskMapper) {
        this.taskDAO = taskDAO;
        this.taskMapper = taskMapper;
    }

    @Override
    @Transactional
    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = taskMapper.toTask(taskRequest);
        task = taskDAO.createTask(task);
        return taskMapper.toDTO(task);
    }

    @Override
    public List<TaskResponse> findTasksByTeamId(Integer teamId) {
        List<Task> tasks = taskDAO.findTasksByTeamId(teamId);
        List<TaskResponse> taskResponses = new ArrayList<>();
        tasks.forEach(task -> taskResponses.add(taskMapper.toDTO(task)));
        return taskResponses;
    }

    @Override
    public TaskResponse findTaskById(Integer id) {
        Task task = taskDAO.findTaskById(id);
        return taskMapper.toDTO(task);
    }

    @Override
    @Transactional
    public TaskResponse updateTask(TaskRequest taskRequest) {
        Task task = taskMapper.toTask(taskRequest);
        task = taskDAO.updateTask(task);
        return taskMapper.toDTO(task);
    }

    @Override
    @Transactional
    public void deleteTask(Integer id) {
        taskDAO.deleteTask(id);
    }

    @Override
    public List<TaskResponse> findTasksByUserId(Integer userId) {
        List<Task> tasks = taskDAO.findTasksByUserId(userId);
        List<TaskResponse> taskResponses = new ArrayList<>();
        tasks.forEach(task -> taskResponses.add(taskMapper.toDTO(task)));
        return taskResponses;
    }

    @Override
    public List<TaskResponse> findTasksByTeamIdAndUserId(Integer teamId, Integer userId) {
        List<Task> tasks = taskDAO.findTasksByTeamIdAndUserId(teamId, userId);
        List<TaskResponse> taskResponses = new ArrayList<>();
        tasks.forEach(task -> taskResponses.add(taskMapper.toDTO(task)));
        return taskResponses;
    }

    @Override
    public List<TaskResponse> findIndividualTasksByUserId(Integer userId) {
        List<Task> tasks = taskDAO.findIndividualTasksByUserId(userId);
        List<TaskResponse> taskResponses = new ArrayList<>();
        tasks.forEach(task -> taskResponses.add(taskMapper.toDTO(task)));
        return taskResponses;
    }

}
