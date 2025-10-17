package com.todolist.dao;

import com.todolist.entity.*;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDAOImplementation implements UserDAO {

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
    public User createUser(User user) {
        if (user.getUserId() != null)
            throw new RuntimeException("id must be null");
        return em.merge(user);
    }

    // ================= READ =================
    @Override
    public User findUserByNickName(String nickName) {
        return em.createQuery("SELECT u FROM User u WHERE u.nickName = :nickName", User.class)
                .setParameter("nickName", nickName)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("User not found with nickname: " + nickName));
    }

    @Override
    public User findUserById(Integer id) {
        return findOrThrow(User.class, id, "User not found with id: " + id);
    }

    @Override
    public UserAuth findUserAuthByUserId(Integer id) {
        return em.createQuery("SELECT ua FROM UserAuth ua WHERE ua.user.userId = :id", UserAuth.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("UserAuth not found for user id: " + id));
    }

    @Override
    public List<Task> findIndividualTaskByUserId(Integer id) {
        List<Task> tasks = em.createQuery(
                        "SELECT t FROM Task t WHERE t.user.userId = :id", Task.class)
                .setParameter("id", id)
                .getResultList();

        if (tasks.isEmpty())
            throw new EntityNotFoundException("No individual tasks found for user id: " + id);

        return tasks;
    }

    @Override
    public List<Team> findTeamByUserId(Integer id) {
        List<Team> teams = em.createQuery(
                        "SELECT ut.team FROM UserAndTeam ut WHERE ut.user.userId = :id", Team.class)
                .setParameter("id", id)
                .getResultList();

        if (teams.isEmpty())
            throw new EntityNotFoundException("No teams found for user id: " + id);

        return teams;
    }

    @Override
    public List<Task> findTeamTaskByUserId(Integer id) {
        List<Task> tasks = em.createQuery(
                        "SELECT t FROM Task t JOIN t.userAndTeam ut WHERE ut.user.userId = :id", Task.class)
                .setParameter("id", id)
                .getResultList();

        if (tasks.isEmpty())
            throw new EntityNotFoundException("No team tasks found for user id: " + id);

        return tasks;
    }

    @Override
    public List<Message> findMessageByUserId(Integer id) {
        List<Message> messages = em.createQuery(
                        "SELECT m FROM Message m JOIN m.userAndTeam ut WHERE ut.user.userId = :id", Message.class)
                .setParameter("id", id)
                .getResultList();

        if (messages.isEmpty())
            throw new EntityNotFoundException("No messages found for user id: " + id);

        return messages;
    }

    // ================= UPDATE =================
    @Override
    public User updateUser(User user) {
        findOrThrow(User.class, user.getUserId(), "Cannot update non-existing user with id: " + user.getUserId());
        return em.merge(user);
    }

    // ================= DELETE =================
    @Override
    public void deleteUserById(Integer id) {
        User user = findOrThrow(User.class, id, "Cannot delete non-existing user with id: " + id);
        em.remove(user);
    }

    @Override
    public void deleteUserByNickName(String nickName) {
        User user = findUserByNickName(nickName);
        em.remove(user);
    }
}
