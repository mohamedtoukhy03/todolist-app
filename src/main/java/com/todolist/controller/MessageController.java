package com.todolist.controller;


import com.todolist.dto.request.MessageRequest;
import com.todolist.dto.response.MessageResponse;
import com.todolist.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{teamId}")
    public List<MessageResponse> getMessageByTeamId(@PathVariable Integer teamId) {
        return  messageService.findMessagesByTeamId(teamId);
    }

    @GetMapping("/{userId}")
    public List<MessageResponse> getMessageByUserId(@PathVariable Integer userId) {
        return messageService.findMessageByUserId(userId);
    }

    @GetMapping("/{userId}/{teamId}")
    public List<MessageResponse> getMessageByUserIdAndTeamId(@PathVariable Integer userId, @PathVariable Integer teamId) {
        return messageService.findMessageByUserIdAndTeamId(userId, teamId);
    }

    @PutMapping("/{id}")
    public MessageResponse updateMessage(@PathVariable Integer id, @RequestBody MessageRequest messageRequest) {
        return messageService.updateMessage(messageRequest);
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
