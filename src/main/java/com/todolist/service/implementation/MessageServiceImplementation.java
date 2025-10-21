package com.todolist.service.implementation;

import com.todolist.dao.MessageDAO;
import com.todolist.entity.Message;
import com.todolist.entity.Team;
import com.todolist.entity.User;
import com.todolist.service.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageServiceImplementation implements MessageService {

    MessageDAO messageDAO;

    public MessageServiceImplementation(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Override
    @Transactional
    public Message createMessage(Message message) {
        return messageDAO.createMessage(message);
    }

    @Override
    public Message findMessageById(Integer id) {
        return messageDAO.findMessageById(id);
    }

    @Override
    @Transactional
    public Message updateMessage(Message message) {
        return messageDAO.updateMessage(message);
    }

    @Override
    @Transactional
    public void deleteMessage(Integer id) {
        messageDAO.deleteMessage(id);
    }

    @Override
    public User findUserByMessageId(Integer messageId) {
        return messageDAO.findUserByMessageId(messageId);
    }

    @Override
    public Team findTeamByMessageId(Integer messageId) {
        return messageDAO.findTeamByMessageId(messageId);
    }
}
