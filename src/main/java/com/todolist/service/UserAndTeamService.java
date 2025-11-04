package com.todolist.service;

import com.todolist.dto.request.UserAndTeamRequest;
import com.todolist.dto.response.UserAndTeamResponse;
import com.todolist.entity.UserAndTeam;
import com.todolist.entity.id.UserTeamId;

public interface UserAndTeamService {
    public UserAndTeamResponse createUserAndTeam(UserAndTeamRequest userAndTeamRequest);
    public UserAndTeamResponse findUserAndTeam(UserTeamId id);
    public void deleteUserAndTeam(UserTeamId id);
    public UserAndTeamResponse updateUserAndTeam(Integer userId, Integer teamId, UserAndTeamRequest userAndTeamRequest);
}
