package com.todolist.dao;

import com.todolist.entity.UserAndTeam;
import com.todolist.entity.id.UserTeamId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class UserAndTeamDAOImplementation implements  UserAndTeamDAO {
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
    public UserAndTeam createUserAndTeam(UserAndTeam userAndTeam) {
        if (userAndTeam.getId() != null)
            throw new RuntimeException("id must be null");
        return (em.merge(userAndTeam));
    }

    // ================= READ =================
    @Override
    public UserAndTeam findUserAndTeam(UserTeamId id) {
        return findOrThrow(UserAndTeam.class, id, "The specified user and team could not be found");
    }

    @Override
    public void deleteUserAndTeam(UserTeamId id) {
        UserAndTeam userAndTeam = findOrThrow(UserAndTeam.class, id, "The user and team could not be found");
        em.remove(userAndTeam);
    }

    // ================= UPDATE =================
    @Override
    public UserAndTeam updateUserAndTeam(UserAndTeam userAndTeam) {
        findOrThrow(UserAndTeam.class, userAndTeam.getId(), "The user and team could not be found");
        return em.merge(userAndTeam);
    }
}
