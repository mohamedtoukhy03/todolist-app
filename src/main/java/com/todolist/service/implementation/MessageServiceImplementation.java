package com.todolist.service.implementation;

import com.todolist.dao.MessageDAO;
import com.todolist.dto.request.MessageRequest;
import com.todolist.dto.response.MessageResponse;
import com.todolist.entity.Message;
import com.todolist.entity.Team;
import com.todolist.entity.User;
import com.todolist.mapper.MessageMapper;
import com.todolist.service.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImplementation implements MessageService {

    MessageDAO messageDAO;
    MessageMapper messageMapper;

    public MessageServiceImplementation(MessageDAO messageDAO, MessageMapper messageMapper) {
        this.messageDAO = messageDAO;
        this.messageMapper = messageMapper;
    }

    @Override
    @Transactional
    public MessageResponse createMessage(MessageRequest messageRequest) {
        Message message = messageMapper.toMessage(messageRequest);
        message = messageDAO.createMessage(message);
        return messageMapper.toDTO(message);
    }

    @Override
    public MessageResponse findMessageById(Integer id) {
        Message message = messageDAO.findMessageById(id);
        return messageMapper.toDTO(message);
    }

    @Override
    @Transactional
    public MessageResponse updateMessage(MessageRequest messageRequest) {
        Message message = messageMapper.toMessage(messageRequest);
        message = messageDAO.updateMessage(message);
        return messageMapper.toDTO(message);
    }



    @Override
    @Transactional
    public void deleteMessage(Integer id) {
        messageDAO.deleteMessage(id);
    }

    @Override
    public List<MessageResponse> findMessagesByTeamId(Integer teamId) {
        List<Message> messages = messageDAO.getMessagesByTeamId(teamId);
        List<MessageResponse> messageResponses = new ArrayList<>();
        messages.forEach((message -> messageResponses.add(messageMapper.toDTO(message))));
        return  messageResponses;
    }

    @Override
    public List<MessageResponse> findMessageByUserId(Integer userId) {
        List<Message> messages = messageDAO.getMessagesByUserId(userId);
        List<MessageResponse> messageResponses = new ArrayList<>();
        messages.forEach((message -> messageResponses.add(messageMapper.toDTO(message))));
        return  messageResponses;
    }

    @Override
    public List<MessageResponse> findMessageByUserIdAndTeamId(Integer userId, Integer teamId) {
        List<Message> messages = messageDAO.getMessageByUserIdAndTeamId(userId, teamId);
        List<MessageResponse> messageResponses = new ArrayList<>();
        messages.forEach((message -> messageResponses.add(messageMapper.toDTO(message))));
        return  messageResponses;
    }
}
