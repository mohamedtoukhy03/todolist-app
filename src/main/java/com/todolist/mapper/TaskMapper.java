package com.todolist.mapper;

import com.todolist.dto.request.TaskRequest;
import com.todolist.dto.response.TaskResponse;
import com.todolist.entity.Task;
import org.springframework.stereotype.Component;


@Component
public class TaskMapper {

    public TaskResponse toDTO(Task task){
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setTaskName(task.getTaskName());
        taskResponse.setTaskId(task.getTaskId());
        taskResponse.setTaskNote(task.getNote());
        return taskResponse;
    }

    public Task toTask(TaskRequest taskResponse){
        Task task = new Task();
        task.setTaskName(taskResponse.getTaskName());
        task.setNote(taskResponse.getTaskNote());
        return task;
    }
}
