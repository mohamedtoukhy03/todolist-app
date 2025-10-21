package com.todolist.mapper;

import com.todolist.dto.response.UserAndTeamResponse;
import com.todolist.entity.UserAndTeam;
import org.springframework.stereotype.Component;


@Component
public class UserAndTeamMapper {
    public UserAndTeamResponse toDTO(UserAndTeam userAndTeam) {
        UserAndTeamResponse userAndTeamResponse = new UserAndTeamResponse();
        userAndTeamResponse.setUserTeamId(userAndTeam.getId());
        return userAndTeamResponse;
    }
}
