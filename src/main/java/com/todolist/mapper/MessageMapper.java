package com.todolist.mapper;

import com.todolist.dto.request.MessageRequest;
import com.todolist.dto.response.MessageResponse;
import com.todolist.entity.Message;
import com.todolist.service.UserAndTeamService;
import org.springframework.stereotype.Component;


@Component
public class MessageMapper {

    private UserAndTeamService userAndTeamService;

    public MessageMapper(UserAndTeamService userAndTeamService) {
        this.userAndTeamService = userAndTeamService;
    }

    public MessageResponse toDTO(Message message){
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage(message.getText());
        messageResponse.setMessageId(message.getMessageId());
        messageResponse.setUserTeamId(message.getUserAndTeam().getId());
        return messageResponse;
    }

    public Message toMessage(MessageRequest messageRequest){
        Message message = new Message();
        message.setText(messageRequest.getMessage());
        message.setUserAndTeam(userAndTeamService.findUserAndTeam(messageRequest.getUserTeamId()));
        return message;
    }
}
