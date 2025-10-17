package com.todolist.dao;

import com.todolist.entity.*;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TeamDAOImplementation implements TeamDAO {

    @PersistenceContext
    private EntityManager em;

    // ================= Utility =================
    private <T> T findOrThrow(Class<T> type, Object id, String message) {
        T entity = em.find(type, id);
        if (entity == null) throw new EntityNotFoundException(message);
        return entity;
    }

    // ================= CREATE =================
    @Override
    public Team createTeam(Team team) {
        if (team.getTeamId() != null)
            throw new RuntimeException("id must be null");
        return (em.merge(team));
    }

    // ================= READ =================
    @Override
    public Team findTeamById(Integer id) {
        return findOrThrow(Team.class, id, "Team not found with id: " + id);
    }

    @Override
    public List<User> findUserByTeamId(Integer id) {
        List<User> users = em.createQuery(
                        "SELECT ut.user FROM UserAndTeam ut WHERE ut.team.teamId = :id",
                        User.class)
                .setParameter("id", id)
                .getResultList();

        if (users.isEmpty())
            throw new EntityNotFoundException("No users found for team with id: " + id);

        return users;
    }

    @Override
    public List<Task> findTaskByTeamId(Integer id) {
        List<Task> tasks = em.createQuery(
                        "SELECT t FROM Task t JOIN t.userAndTeam ut WHERE ut.team.teamId = :id",
                        Task.class)
                .setParameter("id", id)
                .getResultList();

        if (tasks.isEmpty())
            throw new EntityNotFoundException("No tasks found for team with id: " + id);

        return tasks;
    }

    @Override
    public List<Message> findMessageByTeamId(Integer id) {
        List<Message> messages = em.createQuery(
                        "SELECT m FROM Message m JOIN m.userAndTeam ut WHERE ut.team.teamId = :id",
                        Message.class)
                .setParameter("id", id)
                .getResultList();

        if (messages.isEmpty())
            throw new EntityNotFoundException("No messages found for team with id: " + id);

        return messages;
    }

    @Override
    public List<Team> findParentOfTeam(Integer id) {
        List<Team> parents = em.createQuery(
                        "SELECT p FROM Team t JOIN t.parents p WHERE t.teamId = :id",
                        Team.class)
                .setParameter("id", id)
                .getResultList();

        if (parents.isEmpty())
            throw new EntityNotFoundException("No parent teams found for team with id: " + id);

        return parents;
    }

    @Override
    public List<Team> findChildOfTeam(Integer id) {
        List<Team> children = em.createQuery(
                        "SELECT c FROM Team t JOIN t.children c WHERE t.teamId = :id",
                        Team.class)
                .setParameter("id", id)
                .getResultList();

        if (children.isEmpty())
            throw new EntityNotFoundException("No child teams found for team with id: " + id);

        return children;
    }

    // ================= UPDATE =================
    @Override
    public Team updateTeam(Team team) {
        findOrThrow(Team.class, team.getTeamId(),
                "Cannot update non-existing team with id: " + team.getTeamId());
        return em.merge(team);
    }

    // ================= DELETE =================
    @Override
    public void deleteTeamById(Integer id) {
        Team team = findOrThrow(Team.class, id,
                "Cannot delete non-existing team with id: " + id);
        em.remove(team);
    }
}
