package com.todolist.controller;

import com.todolist.dto.request.TeamRequest;
import com.todolist.dto.response.TeamResponse;
import com.todolist.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/{id}")
    public TeamResponse getTeamById(@PathVariable Integer id) {
        return teamService.findTeamById(id);
    }

    @GetMapping("/user/{userId}")
    public List<TeamResponse> getTeamsByUserId(@PathVariable Integer userId) {
        return teamService.findTeamsByUserId(userId);
    }

    @PostMapping
    public TeamResponse createTeam(@Valid @RequestBody TeamRequest teamRequest) {
        return teamService.createTeam(teamRequest);
    }

    @PutMapping("/{id}")
    public TeamResponse updateTeam(@PathVariable Integer id,@Valid @RequestBody TeamRequest teamRequest) {
        return teamService.updateTeam(id, teamRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Integer id) {
        teamService.deleteTeamById(id);
    }
}
