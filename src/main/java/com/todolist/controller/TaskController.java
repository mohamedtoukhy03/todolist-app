package com.todolist.controller;

import com.todolist.dto.request.TaskRequest;
import com.todolist.dto.response.TaskResponse;
import com.todolist.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{userId}/{teamId}")
    public List<TaskResponse> findTasksByUserIdAndTeamId(@PathVariable Integer userId, @PathVariable Integer teamId) {
        return taskService.findTasksByTeamIdAndUserId(userId, teamId);
    }

    @GetMapping("/{teamId}")
    public List<TaskResponse> getTasks(@PathVariable Integer teamId) {
        return taskService.findTasksByTeamId(teamId);
    }

    @GetMapping("/{userId}")
    public List<TaskResponse> getTasksByUserId(@PathVariable Integer userId) {
        return taskService.findTasksByUserId(userId);
    }

    @PostMapping
    public TaskResponse createTask(@RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest);
    }


    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable Integer id) {
        taskService.deleteTask(id);
    }


}
