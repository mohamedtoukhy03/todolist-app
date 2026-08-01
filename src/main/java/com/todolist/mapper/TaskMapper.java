package com.todolist.mapper;

import com.todolist.dao.UserAndTeamDAO;
import com.todolist.dao.UserDAO;
import com.todolist.dto.request.IndividualTaskRequest;
import com.todolist.dto.request.TeamTaskRequest;
import com.todolist.dto.response.TaskResponse;
import com.todolist.entity.Task;
import org.springframework.stereotype.Component;



@Component
public class TaskMapper {

    private UserAndTeamDAO userAndTeamDAO;
    private UserDAO userDAO;

    public TaskMapper(UserAndTeamDAO userAndTeamDAO, UserDAO userDAO) {
        this.userAndTeamDAO = userAndTeamDAO;
        this.userDAO = userDAO;
    }

    public TaskResponse toDTO(Task task){
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setTaskName(task.getTaskName());
        taskResponse.setTaskId(task.getTaskId());
        taskResponse.setTaskNote(task.getNote());
        taskResponse.setStatus(task.getStatus());
        taskResponse.setPriority(task.getPriority());
        taskResponse.setDueDate(task.getDueDate());
        taskResponse.setCreatedAt(task.getCreatedAt());
        taskResponse.setUpdatedAt(task.getUpdatedAt());
        return taskResponse;
    }

    public Task toTask(TeamTaskRequest taskResponse){
        Task task = new Task();
        task.setTaskName(taskResponse.getTaskName());
        task.setNote(taskResponse.getTaskNote());
        if (taskResponse.getStatus() != null) task.setStatus(taskResponse.getStatus());
        if (taskResponse.getPriority() != null) task.setPriority(taskResponse.getPriority());
        if (taskResponse.getDueDate() != null) task.setDueDate(taskResponse.getDueDate());
        taskResponse.getUserTeamIds().forEach(id -> task.addUserAndTeam(userAndTeamDAO.findUserAndTeam(id)));
        return task;
    }

    public Task toTask(IndividualTaskRequest taskResponse){
        Task task = new Task();
        task.setTaskName(taskResponse.getTaskName());
        task.setNote(taskResponse.getTaskNote());
        if (taskResponse.getStatus() != null) task.setStatus(taskResponse.getStatus());
        if (taskResponse.getPriority() != null) task.setPriority(taskResponse.getPriority());
        if (taskResponse.getDueDate() != null) task.setDueDate(taskResponse.getDueDate());
        task.setUser(userDAO.findUserById(taskResponse.getUserId()));
        return task;
    }
}
