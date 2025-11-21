package com.todolist.dao;

import com.todolist.entity.UserAndTeam;
import com.todolist.entity.id.UserTeamId;

public interface UserAndTeamDAO {
    public UserAndTeam createUserAndTeam(UserAndTeam userAndTeam);
    public UserAndTeam findUserAndTeam(UserTeamId id);
    public void deleteUserAndTeam(UserTeamId id);
    public UserAndTeam updateUserAndTeam(UserAndTeam userAndTeam);
}
