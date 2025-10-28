package com.todolist.service;

import com.todolist.dto.request.MessageRequest;
import com.todolist.dto.response.MessageResponse;

import java.util.List;


public interface MessageService {
    public MessageResponse createMessage(MessageRequest messageRequest);
    public MessageResponse findMessageById(Integer id);
    public MessageResponse updateMessage(MessageRequest messageRequest);
    public void deleteMessage(Integer id);
    public List<MessageResponse> findMessagesByTeamId(Integer teamId);
    public List<MessageResponse> findMessageByUserId(Integer userId);
    public List<MessageResponse> findMessageByUserIdAndTeamId(Integer userId, Integer teamId);
}
