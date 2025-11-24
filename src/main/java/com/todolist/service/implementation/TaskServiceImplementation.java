package com.todolist.service.implementation;

import com.todolist.dao.TaskDAO;
import com.todolist.dto.request.IndividualTaskRequest;
import com.todolist.dto.request.TeamTaskRequest;
import com.todolist.dto.response.TaskResponse;
import com.todolist.entity.Task;
import com.todolist.mapper.TaskMapper;
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
    public TaskResponse createTask(TeamTaskRequest taskRequest) {
        Task task = taskMapper.toTask(taskRequest);
        task = taskDAO.createTask(task);
        return taskMapper.toDTO(task);
    }

    @Override
    @Transactional
    public TaskResponse createTask(IndividualTaskRequest taskRequest) {
        Task task = taskMapper.toTask(taskRequest);
        task = taskDAO.createTask(task);
        return taskMapper.toDTO(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> findTasksByTeamId(Integer teamId) {
        List<Task> tasks = taskDAO.findTasksByTeamId(teamId);
        List<TaskResponse> taskResponses = new ArrayList<>();
        tasks.forEach(task -> taskResponses.add(taskMapper.toDTO(task)));
        return taskResponses;
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponse findTaskById(Integer id) {
        Task task = taskDAO.findTaskById(id);
        return taskMapper.toDTO(task);
    }

    @Override
    @Transactional
    public TaskResponse updateTask(Integer id, TeamTaskRequest taskRequest) {
        Task task = taskMapper.toTask(taskRequest);
        task.setTaskId(id);
        task = taskDAO.updateTask(task);
        return taskMapper.toDTO(task);
    }

    @Override
    @Transactional
    public TaskResponse updateTask(Integer id, IndividualTaskRequest taskRequest) {
        Task task = taskMapper.toTask(taskRequest);
        task.setTaskId(id);
        task = taskDAO.updateTask(task);
        return taskMapper.toDTO(task);
    }

    @Override
    @Transactional
    public void deleteTask(Integer id) {
        taskDAO.deleteTask(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> findTasksByUserId(Integer userId) {
        List<Task> tasks = taskDAO.findTasksByUserId(userId);
        List<TaskResponse> taskResponses = new ArrayList<>();
        tasks.forEach(task -> taskResponses.add(taskMapper.toDTO(task)));
        return taskResponses;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> findTasksByTeamIdAndUserId(Integer teamId, Integer userId) {
        List<Task> tasks = taskDAO.findTasksByTeamIdAndUserId(teamId, userId);
        List<TaskResponse> taskResponses = new ArrayList<>();
        tasks.forEach(task -> taskResponses.add(taskMapper.toDTO(task)));
        return taskResponses;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> findIndividualTasksByUserId(Integer userId) {
        List<Task> tasks = taskDAO.findIndividualTasksByUserId(userId);
        List<TaskResponse> taskResponses = new ArrayList<>();
        tasks.forEach(task -> taskResponses.add(taskMapper.toDTO(task)));
        return taskResponses;
    }

}
