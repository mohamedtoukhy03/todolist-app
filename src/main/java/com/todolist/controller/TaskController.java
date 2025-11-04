package com.todolist.controller;

import com.todolist.dto.request.TaskRequest;
import com.todolist.dto.response.TaskResponse;
import com.todolist.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/team/{teamId}")
    public List<TaskResponse> getTasksByTeamId(@PathVariable Integer teamId) {
        return taskService.findTasksByTeamId(teamId);
    }

    @GetMapping("/user/{userId}")
    public List<TaskResponse> getTasksByUserId(@PathVariable Integer userId) {
        return taskService.findTasksByUserId(userId);
    }

    @GetMapping("/user/{userId}/individual")
    public List<TaskResponse> getIndividualTasksByUserId(@PathVariable Integer userId) {
        return taskService.findIndividualTasksByUserId(userId);
    }

    @GetMapping("/user/{userId}/team/{teamId}")
    public List<TaskResponse> getTasksByUserAndTeam(
            @PathVariable Integer userId,
            @PathVariable Integer teamId) {
        return taskService.findTasksByTeamIdAndUserId(teamId, userId);
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
