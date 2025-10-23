package com.todolist.controller;

import com.todolist.dto.request.UserAndTeamRequest;
import com.todolist.dto.response.UserAndTeamResponse;
import com.todolist.entity.UserAndTeam;
import com.todolist.entity.id.UserTeamId;
import com.todolist.mapper.UserAndTeamMapper;
import com.todolist.service.UserAndTeamService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userAndTeam")
public class UserAndTeamController {

    private UserAndTeamService userAndTeamService;
    private UserAndTeamMapper userAndTeamMapper;

    public UserAndTeamController(UserAndTeamService userAndTeamService, UserAndTeamMapper userAndTeamMapper) {
        this.userAndTeamService = userAndTeamService;
        this.userAndTeamMapper = userAndTeamMapper;
    }

    @PostMapping
    public UserAndTeamResponse createUserAndTeam(@RequestBody UserAndTeamRequest userAndTeamRequest) {
        UserAndTeam userAndTeam = userAndTeamMapper.toEntity(userAndTeamRequest);
        userAndTeam =  userAndTeamService.createUserAndTeam(userAndTeam);
        return userAndTeamMapper.toDTO(userAndTeam);
    }

    @GetMapping("/{teamId}/{userId}")
    public UserAndTeamResponse getUserAndTeamById(@PathVariable Integer teamId, @PathVariable Integer userId) {
        UserTeamId userTeamId = new UserTeamId(teamId, userId);
        UserAndTeam userAndTeam = userAndTeamService.findUserAndTeam(userTeamId);
        return userAndTeamMapper.toDTO(userAndTeam);
    }

    @DeleteMapping("/{teamId}/{userId}")
    public void deleteUserAndTeam(@PathVariable Integer teamId, @PathVariable Integer userId) {
        UserTeamId userTeamId = new UserTeamId(teamId, userId);
        userAndTeamService.deleteUserAndTeam(userTeamId);
    }
}
