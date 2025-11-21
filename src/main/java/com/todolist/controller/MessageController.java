package com.todolist.controller;

import com.todolist.dto.request.MessageRequest;
import com.todolist.dto.response.MessageResponse;
import com.todolist.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{id}")
    public MessageResponse getMessageById(@PathVariable Integer id) {
        return messageService.findMessageById(id);
    }

    @GetMapping("/team/{teamId}")
    public List<MessageResponse> getMessagesByTeamId(@PathVariable Integer teamId) {
        return messageService.findMessagesByTeamId(teamId);
    }

    @GetMapping("/user/{userId}")
    public List<MessageResponse> getMessagesByUserId(@PathVariable Integer userId) {
        return messageService.findMessageByUserId(userId);
    }

    @GetMapping("/user/{userId}/team/{teamId}")
    public List<MessageResponse> getMessagesByUserIdAndTeamId(
            @PathVariable Integer userId,
            @PathVariable Integer teamId) {
        return messageService.findMessageByUserIdAndTeamId(userId, teamId);
    }

    @PostMapping
    public MessageResponse addMessage(@Valid @RequestBody MessageRequest messageRequest) {
        return messageService.createMessage(messageRequest);
    }

    @PutMapping("/{id}")
    public MessageResponse updateMessage(@PathVariable Integer id, @Valid @RequestBody MessageRequest messageRequest) {
        return messageService.updateMessage(id, messageRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteMessageById(@PathVariable Integer id) {
        messageService.deleteMessage(id);
    }
}
