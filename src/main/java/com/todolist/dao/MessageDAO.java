package com.todolist.dao;

import com.todolist.entity.Message;

import java.util.List;

public interface MessageDAO {
    public Message createMessage(Message message);
    public Message findMessageById(Integer id);
    public Message updateMessage(Message message);
    public void deleteMessage(Integer id);
    public List<Message> getMessagesByTeamId(Integer teamId);
    public List<Message> getMessagesByUserId(Integer userId);
    public List<Message> getMessageByUserIdAndTeamId(Integer userId, Integer teamId);

}
