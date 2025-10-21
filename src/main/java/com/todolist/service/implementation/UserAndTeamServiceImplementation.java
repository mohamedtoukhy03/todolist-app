package com.todolist.service.implementation;

import com.todolist.dao.UserAndTeamDAO;
import com.todolist.entity.UserAndTeam;
import com.todolist.entity.id.UserTeamId;
import com.todolist.service.UserAndTeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAndTeamServiceImplementation implements UserAndTeamService {

    UserAndTeamDAO userAndTeamDAO;

    public UserAndTeamServiceImplementation(UserAndTeamDAO userAndTeamDAO) {
        this.userAndTeamDAO = userAndTeamDAO;
    }

    @Override
    @Transactional
    public UserAndTeam createUserAndTeam(UserAndTeam userAndTeam) {
        return userAndTeamDAO.createUserAndTeam(userAndTeam);
    }

    @Override
    public UserAndTeam findUserAndTeam(UserTeamId id) {
        return userAndTeamDAO.findUserAndTeam(id);
    }

    @Override
    @Transactional
    public void deleteUserAndTeam(UserTeamId id) {
        userAndTeamDAO.deleteUserAndTeam(id);
    }

    @Override
    @Transactional
    public UserAndTeam updateUserAndTeam(UserAndTeam userAndTeam) {
        return userAndTeamDAO.updateUserAndTeam(userAndTeam);
    }
}
