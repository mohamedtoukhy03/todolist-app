package com.todolist.dao.implementation;

import com.todolist.dao.UserAndTeamDAO;
import com.todolist.entity.UserAndTeam;
import com.todolist.entity.id.UserTeamId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserAndTeamDAOImplementation implements UserAndTeamDAO {
    @PersistenceContext
    private EntityManager em;

    private <T> T findOrThrow(Class<T> type, Object id, String message) {
        T entity = em.find(type, id);
        if (entity == null) throw new EntityNotFoundException(message);
        return entity;
    }

    @Override
    public UserAndTeam createUserAndTeam(UserAndTeam userAndTeam) {
        return (em.merge(userAndTeam));
    }

    @Override
    public UserAndTeam findUserAndTeam(UserTeamId id) {
        return findOrThrow(UserAndTeam.class, id, "The specified user and team could not be found");
    }

    @Override
    public void deleteUserAndTeam(UserTeamId id) {
        UserAndTeam userAndTeam = findOrThrow(UserAndTeam.class, id, "The user and team could not be found");
        em.remove(userAndTeam);
    }

    @Override
    public UserAndTeam updateUserAndTeam(UserAndTeam userAndTeam) {
        findOrThrow(UserAndTeam.class, userAndTeam.getId(), "The user and team could not be found");
        return em.merge(userAndTeam);
    }
}
