package com.todolist.dao.implementation;

import com.todolist.dao.MessageDAO;
import com.todolist.dto.response.MessageResponse;
import com.todolist.entity.Message;
import com.todolist.entity.Team;
import com.todolist.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDAOImplementation implements MessageDAO {

    @PersistenceContext
    private EntityManager em;

    private <T> T findOrThrow(Class<T> type, Object id, String message) {
        T entity = em.find(type, id);
        if (entity == null) throw new EntityNotFoundException(message);
        return entity;
    }

    @Override
    public Message createMessage(Message message) {
        if (message.getMessageId() != null)
            throw new RuntimeException("id must be null");
        return (em.merge(message));
    }

    @Override
    public Message findMessageById(Integer id) {
        return findOrThrow(Message.class, id, "Message not found with id: " + id);
    }


    @Override
    public Message updateMessage(Message message) {
        findOrThrow(Message.class, message.getMessageId(),
                "Cannot update non-existing message with id: " + message.getMessageId());
        return em.merge(message);
    }

    @Override
    public void deleteMessage(Integer id) {
        Message message = findOrThrow(Message.class, id,
                "Cannot delete non-existing message with id: " + id);
        em.remove(message);
    }

    @Override
    public List<Message> getMessagesByTeamId(Integer teamId) {
        List<Message> messages = em.createQuery(
                "SELECT m FROM Message m WHERE m.userAndTeam.id.teamId = :teamId", Message.class)
                .setParameter("teamId", teamId)
                .getResultList();
        if (messages.isEmpty())
            throw new EntityNotFoundException("No messages found with teamId: " + teamId);
        return messages;
    }

    @Override
    public List<Message> getMessagesByUserId(Integer userId) {
        List<Message> messages = em.createQuery(
                        "SELECT m FROM Message m WHERE m.userAndTeam.id.userId = :userId", Message.class)
                .setParameter("userId", userId)
                .getResultList();
        if (messages.isEmpty())
            throw new EntityNotFoundException("No messages found with userId: " + userId);
        return messages;
    }

    @Override
    public List<Message> getMessageByUserIdAndTeamId(Integer userId, Integer teamId) {
        List<Message> messages = em.createQuery(
                        "SELECT m FROM Message m WHERE m.userAndTeam.id.userId = :userId and m.userAndTeam.id.teamId = :teamId", Message.class)
                .setParameter("userId", userId)
                .setParameter("teamId", teamId)
                .getResultList();

        if (messages.isEmpty())
            throw new EntityNotFoundException("No messages found with userId: " + userId);
        return messages;
    }
}
