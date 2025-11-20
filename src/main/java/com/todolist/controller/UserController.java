package com.todolist.controller;

import com.todolist.dto.request.UserRequest;
import com.todolist.dto.response.UserResponse;
import com.todolist.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

    @GetMapping("/team/{teamId}")
    public List<UserResponse> getUsersByTeamId(@PathVariable Integer teamId) {
        return userService.findUsersByTeamId(teamId);
    }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Integer id, @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
    }
}
