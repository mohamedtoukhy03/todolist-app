package com.todolist.service;

import com.todolist.dao.TaskDAO;
import com.todolist.entity.Task;
import com.todolist.entity.Team;
import com.todolist.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImplementation implements TaskService {

    TaskDAO taskDAO;

    public TaskServiceImplementation(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Override
    @Transactional
    public Task createTask(Task task) {
        return taskDAO.createTask(task);
    }

    @Override
    public Task findTaskById(Integer id) {
        return taskDAO.findTaskById(id);
    }

    @Override
    @Transactional
    public Task updateTask(Task task) {
        return taskDAO.updateTask(task);
    }

    @Override
    @Transactional
    public void deleteTask(Integer id) {
        taskDAO.deleteTask(id);
    }

    @Override
    public List<Team> findTeamByTaskId(Integer taskId) {
        return taskDAO.findTeamByTaskId(taskId);
    }

    @Override
    public List<User> findUserWithTeamTaskByTaskId(Integer taskId) {
        return taskDAO.findUserWithTeamTaskByTaskId(taskId);
    }

    @Override
    public User findUserWithIndividualTaskByTaskId(Integer taskId) {
        return taskDAO.findUserWithIndividualTaskByTaskId(taskId);
    }
}
