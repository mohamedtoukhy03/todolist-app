package com.todolist.controller;

import com.todolist.dto.request.TaskRequest;
import com.todolist.dto.response.TaskResponse;
import com.todolist.entity.Task;
import com.todolist.mapper.TaskMapper;
import com.todolist.service.TaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {
    TaskService taskService;
    TaskMapper taskMapper;


    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Integer id) {
        Task task = taskService.findTaskById(id);
        return taskMapper.toDTO(task);
    }

    @PostMapping
    public TaskResponse createTask(@RequestBody TaskRequest taskRequest) {
        Task task = taskMapper.toTask(taskRequest);
        task = taskService.createTask(task);
        return taskMapper.toDTO(task);
    }


    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable Integer id) {
        taskService.deleteTask(id);
    }


}
