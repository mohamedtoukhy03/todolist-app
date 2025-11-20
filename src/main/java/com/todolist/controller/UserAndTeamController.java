package com.todolist.controller;

import com.todolist.dto.request.UserAndTeamRequest;
import com.todolist.dto.response.UserAndTeamResponse;
import com.todolist.entity.id.UserTeamId;
import com.todolist.service.UserAndTeamService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-teams")
public class UserAndTeamController {

    private final UserAndTeamService userAndTeamService;

    public UserAndTeamController(UserAndTeamService userAndTeamService) {
        this.userAndTeamService = userAndTeamService;
    }

    @PostMapping
    public UserAndTeamResponse createUserAndTeam(@Valid @RequestBody UserAndTeamRequest userAndTeamRequest) {
        return userAndTeamService.createUserAndTeam(userAndTeamRequest);
    }

    @GetMapping("/user/{userId}/team/{teamId}")
    public UserAndTeamResponse getUserAndTeamById(@PathVariable Integer userId, @PathVariable Integer teamId) {
        UserTeamId userTeamId = new UserTeamId(userId, teamId);
        return userAndTeamService.findUserAndTeam(userTeamId);
    }


    @PutMapping("/user/{userId}/team/{teamId}")
    public UserAndTeamResponse updateUserAndTeam(
            @PathVariable Integer userId,
            @PathVariable Integer teamId,
            @Valid @RequestBody UserAndTeamRequest userAndTeamRequest) {
        return userAndTeamService.updateUserAndTeam(userId, teamId, userAndTeamRequest);
    }

    @DeleteMapping("/user/{userId}/team/{teamId}")
    public void deleteUserAndTeam(@PathVariable Integer userId, @PathVariable Integer teamId) {
        UserTeamId userTeamId = new UserTeamId(userId, teamId);
        userAndTeamService.deleteUserAndTeam(userTeamId);
    }
}
