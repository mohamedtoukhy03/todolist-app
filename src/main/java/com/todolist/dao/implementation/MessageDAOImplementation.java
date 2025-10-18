package com.todolist.dao;

import com.todolist.entity.Message;
import com.todolist.entity.Team;
import com.todolist.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MessageDAOImplementation implements MessageDAO {

    @PersistenceContext
    private EntityManager em;

    // ================= UTILITY =================
    private <T> T findOrThrow(Class<T> type, Object id, String message) {
        T entity = em.find(type, id);
        if (entity == null) throw new EntityNotFoundException(message);
        return entity;
    }

    // ================= CREATE =================
    @Override
    public Message createMessage(Message message) {
        if (message.getMessageId() != null)
            throw new RuntimeException("id must be null");
        return (em.merge(message));
    }

    // ================= READ =================
    @Override
    public Message findMessageById(Integer id) {
        return findOrThrow(Message.class, id, "Message not found with id: " + id);
    }

    @Override
    public User findUserByMessageId(Integer messageId) {
        return em.createQuery(
                        "SELECT ut.user FROM Message m JOIN m.userAndTeam ut WHERE m.messageId = :messageId",
                        User.class
                )
                .setParameter("messageId", messageId)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("User not found for message id: " + messageId));
    }

    @Override
    public Team findTeamByMessageId(Integer messageId) {
        return em.createQuery(
                        "SELECT ut.team FROM Message m JOIN m.userAndTeam ut WHERE m.messageId = :messageId",
                        Team.class
                )
                .setParameter("messageId", messageId)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Team not found for message id: " + messageId));
    }

    // ================= UPDATE =================
    @Override
    public Message updateMessage(Message message) {
        findOrThrow(Message.class, message.getMessageId(),
                "Cannot update non-existing message with id: " + message.getMessageId());
        return em.merge(message);
    }

    // ================= DELETE =================
    @Override
    public void deleteMessage(Integer id) {
        Message message = findOrThrow(Message.class, id,
                "Cannot delete non-existing message with id: " + id);
        em.remove(message);
    }
}
