package com.todolist.mapper;

import com.todolist.dao.UserAndTeamDAO;
import com.todolist.dto.request.MessageRequest;
import com.todolist.dto.response.MessageResponse;
import com.todolist.entity.Message;
import org.springframework.stereotype.Component;


@Component
public class MessageMapper {

    private UserAndTeamDAO userAndTeamDAO;

    public MessageMapper(UserAndTeamDAO userAndTeamDAO) {
        this.userAndTeamDAO = userAndTeamDAO;
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
        message.setUserAndTeam(userAndTeamDAO.findUserAndTeam(messageRequest.getUserTeamId()));
        return message;
    }
}
