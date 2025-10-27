package com.todolist.dao.implementation;

import com.todolist.dao.TeamDAO;
import com.todolist.entity.*;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamDAOImplementation implements TeamDAO {

    @PersistenceContext
    private EntityManager em;

    private <T> T findOrThrow(Class<T> type, Object id, String message) {
        T entity = em.find(type, id);
        if (entity == null) throw new EntityNotFoundException(message);
        return entity;
    }

    @Override
    public Team createTeam(Team team) {
        if (team.getTeamId() != null)
            throw new RuntimeException("id must be null");
        return (em.merge(team));
    }

    @Override
    public Team findTeamById(Integer id) {
        return findOrThrow(Team.class, id, "Team not found with id: " + id);
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

    @Override
    public Team updateTeam(Team team) {
        findOrThrow(Team.class, team.getTeamId(),
                "Cannot update non-existing team with id: " + team.getTeamId());
        return em.merge(team);
    }

    @Override
    public void deleteTeamById(Integer id) {
        Team team = findOrThrow(Team.class, id,
                "Cannot delete non-existing team with id: " + id);
        em.remove(team);
    }
}
