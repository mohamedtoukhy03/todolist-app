package com.todolist.mapper;

import com.todolist.dao.UserAndTeamDAO;
import com.todolist.dto.request.TaskRequest;
import com.todolist.dto.response.TaskResponse;
import com.todolist.entity.Task;
import com.todolist.entity.UserAndTeam;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class TaskMapper {

    private UserAndTeamDAO userAndTeamDAO;

    public TaskMapper(UserAndTeamDAO userAndTeamDAO) {
        this.userAndTeamDAO = userAndTeamDAO;
    }

    public TaskResponse toDTO(Task task){
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setTaskName(task.getTaskName());
        taskResponse.setTaskId(task.getTaskId());
        taskResponse.setTaskNote(task.getNote());
        taskResponse.setUserTeamIds(task.getUserAndTeam().stream()
                .map(UserAndTeam::getId)
                .collect(Collectors.toList())
        );
        return taskResponse;
    }

    public Task toTask(TaskRequest taskResponse){
        Task task = new Task();
        task.setTaskName(taskResponse.getTaskName());
        task.setNote(taskResponse.getTaskNote());
        taskResponse.getUserTeamIds().forEach(id -> task.addUserAndTeam(userAndTeamDAO.findUserAndTeam(id)));
        return task;
    }
}
