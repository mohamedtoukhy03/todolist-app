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
        return taskResponse;
    }

    public Task toTask(TeamTaskRequest taskResponse){
        Task task = new Task();
        task.setTaskName(taskResponse.getTaskName());
        task.setNote(taskResponse.getTaskNote());
        taskResponse.getUserTeamIds().forEach(id -> task.addUserAndTeam(userAndTeamDAO.findUserAndTeam(id)));
        return task;
    }

    public Task toTask(IndividualTaskRequest taskResponse){
        Task task = new Task();
        task.setTaskName(taskResponse.getTaskName());
        task.setNote(taskResponse.getTaskNote());
        task.setUser(userDAO.findUserById(taskResponse.getUserId()));
        return task;
    }
}
