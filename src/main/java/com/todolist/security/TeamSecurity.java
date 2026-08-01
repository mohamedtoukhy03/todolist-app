package com.todolist.security;

import com.todolist.dao.UserDAO;
import com.todolist.entity.User;
import com.todolist.entity.UserAndTeam;
import com.todolist.entity.enums.TeamRole;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("teamSecurity")
public class TeamSecurity {

    private final UserDAO userDAO;

    public TeamSecurity(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean isTeamOwner(Integer teamId, String email) {
        Optional<User> userOpt = userDAO.findUserByEmail(email);
        if (userOpt.isEmpty() || teamId == null) return false;

        User user = userOpt.get();
        if (user.getUserAndTeam() == null) return false;

        return user.getUserAndTeam().stream()
                .filter(ut -> ut.getId().getTeamId().equals(teamId))
                .anyMatch(ut -> ut.getTeamRole() == TeamRole.TEAM_OWNER);
    }

    public boolean isTeamAdminOrOwner(Integer teamId, String email) {
        Optional<User> userOpt = userDAO.findUserByEmail(email);
        if (userOpt.isEmpty() || teamId == null) return false;

        User user = userOpt.get();
        if (user.getUserAndTeam() == null) return false;

        return user.getUserAndTeam().stream()
                .filter(ut -> ut.getId().getTeamId().equals(teamId))
                .anyMatch(ut -> ut.getTeamRole() == TeamRole.TEAM_OWNER || ut.getTeamRole() == TeamRole.TEAM_ADMIN);
    }
}
