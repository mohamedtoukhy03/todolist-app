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
    TeamMapper teamMapper;

    public TeamController(TeamService teamService,  TeamMapper teamMapper) {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
    }

    @GetMapping("/{id}")
    public TeamResponse getTeam(@PathVariable Integer id) {
        Team team = teamService.findTeamById(id);
        return teamMapper.toDTO(team);
    }

    @PostMapping
    public TeamResponse addTeam(@RequestBody TeamRequest teamRequest) {
        Team team = teamMapper.toEntity(teamRequest);
        team = teamService.createTeam(team);
        return teamMapper.toDTO(team);
    }

    @PatchMapping("/{id}")
    public TeamResponse updateTeam(@PathVariable Integer id, @RequestBody Map<String, Object> map) {
        Team team = teamService.applyTeam(teamService.findTeamById(id), map);
        team = teamService.updateTeam(team);
        return teamMapper.toDTO(team);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Integer id) {
        teamService.deleteTeamById(id);
    }
}
