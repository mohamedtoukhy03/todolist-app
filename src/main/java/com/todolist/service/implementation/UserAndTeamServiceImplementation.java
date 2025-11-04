package com.todolist.service.implementation;

import com.todolist.dao.UserAndTeamDAO;
import com.todolist.dto.request.UserAndTeamRequest;
import com.todolist.dto.response.UserAndTeamResponse;
import com.todolist.entity.UserAndTeam;
import com.todolist.entity.id.UserTeamId;
import com.todolist.mapper.UserAndTeamMapper;
import com.todolist.service.UserAndTeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAndTeamServiceImplementation implements UserAndTeamService {

    UserAndTeamDAO userAndTeamDAO;
    UserAndTeamMapper userAndTeamMapper;

    public UserAndTeamServiceImplementation(UserAndTeamDAO userAndTeamDAO, UserAndTeamMapper userAndTeamMapper) {
        this.userAndTeamDAO = userAndTeamDAO;
        this.userAndTeamMapper = userAndTeamMapper;
    }

    @Override
    @Transactional
    public UserAndTeamResponse createUserAndTeam(UserAndTeamRequest userAndTeamRequest) {
        UserAndTeam userAndTeam = userAndTeamMapper.toEntity(userAndTeamRequest);
        userAndTeam =  userAndTeamDAO.createUserAndTeam(userAndTeam);
        return userAndTeamMapper.toDTO(userAndTeam);
    }

    @Override
    public UserAndTeamResponse findUserAndTeam(UserTeamId id) {
        UserAndTeam userAndTeam = userAndTeamDAO.findUserAndTeam(id);
        return userAndTeamMapper.toDTO(userAndTeam);
    }

    @Override
    @Transactional
    public void deleteUserAndTeam(UserTeamId id) {
        userAndTeamDAO.deleteUserAndTeam(id);
    }

    @Override
    @Transactional
    public UserAndTeamResponse updateUserAndTeam(Integer userId, Integer teamId, UserAndTeamRequest userAndTeamRequest) {
        UserAndTeam userAndTeam = userAndTeamMapper.toEntity(userAndTeamRequest);
        userAndTeam = userAndTeamDAO.updateUserAndTeam(userId, teamId, userAndTeam);
        return userAndTeamMapper.toDTO(userAndTeam);
    }
}
