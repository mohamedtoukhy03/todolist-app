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

    public  MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{id}")
    public MessageResponse getMessageById(@PathVariable Integer id) {
        return messageService.findMessageById(id);
    }

    @PostMapping
    public MessageResponse addMessage(@RequestBody MessageRequest messageRequest) {
       return messageService.createMessage(messageRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteMessageById(@PathVariable Integer id) {
        messageService.deleteMessage(id);
    }
}
