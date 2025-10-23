package com.todolist.mapper;

import com.todolist.dto.request.TaskRequest;
import com.todolist.dto.response.TaskResponse;
import com.todolist.entity.Task;
import com.todolist.entity.UserAndTeam;
import com.todolist.service.UserAndTeamService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class TaskMapper {

    private UserAndTeamService userAndTeamService;

    public TaskMapper(UserAndTeamService userAndTeamService) {
        this.userAndTeamService = userAndTeamService;
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
        taskResponse.getUserTeamIds().forEach(id -> task.addUserAndTeam(userAndTeamService.findUserAndTeam(id)));
        return task;
    }
}
