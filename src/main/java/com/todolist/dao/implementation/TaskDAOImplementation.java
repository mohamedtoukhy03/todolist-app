package com.todolist.dao.implementation;

import com.todolist.dao.TaskDAO;
import com.todolist.entity.Task;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TaskDAOImplementation implements TaskDAO {

    @PersistenceContext
    private EntityManager em;

    private <T> T findOrThrow(Class<T> type, Object id, String message) {
        T entity = em.find(type, id);
        if (entity == null) throw new EntityNotFoundException(message);
        return entity;
    }

    @Override
    public Task createTask(Task task) {
        if (task.getTaskId() != null)
            throw new RuntimeException("id must be null");
        return (em.merge(task));
    }

    @Override
    public List<Task> findTasksByTeamId(Integer teamId) {
        List<Task> tasks = em.createQuery(
                "SELECT t FROM Task t JOIN t.userAndTeam ut WHERE ut.id.teamId = :teamId", Task.class)
                        .setParameter("teamId", teamId)
                        .getResultList();
        if (tasks.isEmpty())
            throw new EntityNotFoundException("No tasks found for team " + teamId);
        return tasks;
    }


    @Override
    public Task findTaskById(Integer id) {
        return findOrThrow(Task.class, id, "Task with id " + id + " not found");
    }


    @Override
    public Task updateTask(Task task) {
        findOrThrow(Task.class, task.getTaskId(), "Task with id " + task.getTaskId() + " not found");
        return em.merge(task);
    }

    @Override
    public void deleteTask(Integer id) {
        Task task = findOrThrow(Task.class, id, "Task with id " + id + " not found");
        em.remove(task);
    }

    @Override
    public List<Task> findTasksByUserId(Integer userId) {
        List<Task> tasks = em.createQuery(
                "SELECT t FROM Task t JOIN t.userAndTeam ut WHERE ut.id.userId = :userId", Task.class)
                .setParameter("userId", userId)
                .getResultList();
        if (tasks.isEmpty())
            throw new EntityNotFoundException("No tasks found for user " + userId);
        return tasks;
    }

    @Override
    public List<Task> findTasksByTeamIdAndUserId(Integer teamId, Integer userId) {
        List<Task> tasks = em.createQuery(
                "SELECT t FROM Task t JOIN t.userAndTeam ut WHERE ut.id.userId = :userId AND ut.id.teamId = :teamId", Task.class)
                .setParameter("userId", userId)
                .setParameter("teamId", teamId)
                .getResultList();
        if (tasks.isEmpty())
            throw new EntityNotFoundException("No tasks found for team " + teamId);
        return tasks;
    }

    @Override
    public List<Task> findIndividualTasksByUserId(Integer userId) {
        List<Task> tasks = em.createQuery(
                "SELECT t FROM Task t WHERE t.user.userId = :userId", Task.class)
                .setParameter("userId", userId)
                .getResultList();
        if (tasks.isEmpty())
            throw new EntityNotFoundException("No tasks found for user " + userId);
        return tasks;
    }
}
