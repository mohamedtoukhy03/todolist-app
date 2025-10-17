package com.todolist.dao;

import com.todolist.entity.Message;
import com.todolist.entity.Team;
import com.todolist.entity.User;

import java.util.List;

public interface MessageDAO {
    // CRUD features
    public Message createMessage(Message message);
    public Message findMessageById(Integer id);
    public Message updateMessage(Message message);
    public void deleteMessage(Integer id);
    public User findUserByMessageId(Integer messageId);
    public Team findTeamByMessageId(Integer messageId);
}
