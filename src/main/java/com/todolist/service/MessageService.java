package com.todolist.service;

import com.todolist.dto.request.MessageRequest;
import com.todolist.dto.response.MessageResponse;

public interface MessageService {
    public MessageResponse createMessage(MessageRequest messageRequest);
    public MessageResponse findMessageById(Integer id);
    public MessageResponse updateMessage(MessageRequest messageRequest);
    public void deleteMessage(Integer id);
}
