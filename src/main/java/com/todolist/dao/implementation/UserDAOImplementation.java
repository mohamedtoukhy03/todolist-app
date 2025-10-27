package com.todolist.dao.implementation;

import com.todolist.dao.UserDAO;
import com.todolist.entity.*;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImplementation implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    private <T> T findOrThrow(Class<T> type, Object id, String message) {
        T entity = em.find(type, id);
        if (entity == null) throw new EntityNotFoundException(message);
        return entity;
    }

    @Override
    public User createUser(User user) {
        if (user.getUserId() != null)
            throw new RuntimeException("id must be null");
        return em.merge(user);
    }

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
    public User updateUser(User user) {
        findOrThrow(User.class, user.getUserId(), "Cannot update non-existing user with id: " + user.getUserId());
        return em.merge(user);
    }

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
