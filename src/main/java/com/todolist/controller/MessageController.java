package com.todolist.controller;


import com.todolist.dto.request.MessageRequest;
import com.todolist.dto.response.MessageResponse;
import com.todolist.entity.Message;
import com.todolist.mapper.MessageMapper;
import com.todolist.service.MessageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {
    MessageService messageService;
    MessageMapper messageMapper;

    public  MessageController(MessageService messageService, MessageMapper messageMapper) {
        this.messageService = messageService;
        this.messageMapper = messageMapper;
    }

    @GetMapping("/{id}")
    public MessageResponse getMessageById(@PathVariable Integer id) {
        Message message = messageService.findMessageById(id);
        return messageMapper.toDTO(message);
    }

    @PostMapping
    public MessageResponse addMessage(@RequestBody MessageRequest messageRequest) {
        Message message = messageMapper.toMessage(messageRequest);
        message = messageService.createMessage(message);
        return messageMapper.toDTO(message);
    }

    @DeleteMapping("/{id}")
    public void deleteMessageById(@PathVariable Integer id) {
        messageService.deleteMessage(id);
    }



}
