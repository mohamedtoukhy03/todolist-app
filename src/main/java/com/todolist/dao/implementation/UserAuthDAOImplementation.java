package com.todolist.dao;

import com.todolist.entity.UserAuth;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserAuthDAOImplementation implements UserAuthDAO{
    @PersistenceContext
    private EntityManager em;

    // ================= UTILITY =================
    private <T> T findOrThrow(Class<T> type, Object id, String message) {
        T entity = em.find(type, id);
        if (entity == null) throw new EntityNotFoundException(message);
        return entity;
    }

    @Override
    public UserAuth createUserAuth(UserAuth userAuth) {
        if (userAuth.getUserId() != null)
            throw new RuntimeException("id must be null");
        return (em.merge(userAuth));
    }

    @Override
    public UserAuth updateUserAuth(UserAuth userAuth) {
        findOrThrow(UserAuth.class, userAuth.getUserId(), "UserAuth does not exist");
        return em.merge(userAuth);
    }

    @Override
    public void deleteUserAuth(UserAuth userAuth) {
        userAuth = findOrThrow(UserAuth.class, userAuth.getUserId(), "UserAuth does not exist");
        em.remove(userAuth);
    }

    @Override
    public UserAuth findUserAuthById(Integer id) {
        return findOrThrow(UserAuth.class, id, "UserAuth does not exist");
    }
}
