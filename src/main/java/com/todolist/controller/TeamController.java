package com.todolist.controller;


import com.todolist.dto.request.TeamRequest;
import com.todolist.dto.response.TeamResponse;
import com.todolist.entity.Team;
import com.todolist.mapper.TeamMapper;
import com.todolist.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/team")
public class TeamController {
    TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/{id}")
    public TeamResponse getTeam(@PathVariable Integer id) {
       return teamService.findTeamById(id);
    }

    @PostMapping
    public TeamResponse addTeam(@RequestBody TeamRequest teamRequest) {
       return teamService.createTeam(teamRequest);
    }

    @PatchMapping("/{id}")
    public TeamResponse updateTeam(@PathVariable Integer id, @RequestBody Map<String, Object> map) {
        return teamService.applyTeam(id, map);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Integer id) {
        teamService.deleteTeamById(id);
    }
}
