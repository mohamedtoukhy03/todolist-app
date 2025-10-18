package com.todolist.dao;

import com.todolist.entity.Task;
import com.todolist.entity.Team;
import com.todolist.entity.User;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    // ================= CREATE =================
    @Override
    public Task createTask(Task task) {
        if (task.getTaskId() != null)
            throw new RuntimeException("id must be null");
        return (em.merge(task));
    }

    // ================= READ =================
    @Override
    public Task findTaskById(Integer id) {
        return findOrThrow(Task.class, id, "Task with id " + id + " not found");
    }

    @Override
    public List<Team> findTeamByTaskId(Integer taskId) {
        List<Team> teams = em.createQuery(
                        "SELECT ut.team FROM Task t JOIN t.userAndTeam ut WHERE t.taskId = :taskId", Team.class)
                .setParameter("taskId", taskId)
                .getResultList();

        if (teams.isEmpty())
            throw new EntityNotFoundException("No teams found for task " + taskId);

        return teams;
    }

    @Override
    public List<User> findUserWithTeamTaskByTaskId(Integer taskId) {
        List<User> users = em.createQuery(
                        "SELECT ut.user FROM Task t JOIN t.userAndTeam ut WHERE t.taskId = :taskId", User.class)
                .setParameter("taskId", taskId)
                .getResultList();

        if (users.isEmpty())
            throw new EntityNotFoundException("No users found for team task " + taskId);

        return users;
    }

    @Override
    public User findUserWithIndividualTaskByTaskId(Integer taskId) {
        return em.createQuery(
                        "SELECT t.user FROM Task t WHERE t.taskId = :taskId", User.class)
                .setParameter("taskId", taskId)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("User for task " + taskId + " not found"));
    }

    // ================= UPDATE =================
    @Override
    public Task updateTask(Task task) {
        findOrThrow(Task.class, task.getTaskId(), "Task with id " + task.getTaskId() + " not found");
        return em.merge(task);
    }

    // ================= DELETE =================
    @Override
    public void deleteTask(Integer id) {
        Task task = findOrThrow(Task.class, id, "Task with id " + id + " not found");
        em.remove(task);
    }
}
