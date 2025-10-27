package com.todolist.controller;

import com.todolist.dto.request.UserAndTeamRequest;
import com.todolist.dto.response.UserAndTeamResponse;
import com.todolist.entity.id.UserTeamId;
import com.todolist.service.UserAndTeamService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userAndTeam")
public class UserAndTeamController {

    private UserAndTeamService userAndTeamService;

    public UserAndTeamController(UserAndTeamService userAndTeamService) {
        this.userAndTeamService = userAndTeamService;
    }

    @PostMapping
    public UserAndTeamResponse createUserAndTeam(@RequestBody UserAndTeamRequest userAndTeamRequest) {
       return userAndTeamService.createUserAndTeam(userAndTeamRequest);
    }

    @GetMapping("/{teamId}/{userId}")
    public UserAndTeamResponse getUserAndTeamById(@PathVariable Integer teamId, @PathVariable Integer userId) {
        UserTeamId userTeamId = new UserTeamId(teamId, userId);
        return userAndTeamService.findUserAndTeam(userTeamId);
    }

    @DeleteMapping("/{teamId}/{userId}")
    public void deleteUserAndTeam(@PathVariable Integer teamId, @PathVariable Integer userId) {
        UserTeamId userTeamId = new UserTeamId(teamId, userId);
        userAndTeamService.deleteUserAndTeam(userTeamId);
    }
}
