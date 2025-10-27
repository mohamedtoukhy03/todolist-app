package com.todolist.controller;

import com.todolist.dto.request.UserRequest;
import com.todolist.dto.response.UserResponse;
import com.todolist.entity.User;
import com.todolist.entity.UserAuth;
import com.todolist.mapper.UserMapper;
import com.todolist.service.UserAuthService;
import com.todolist.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;
    UserAuthService userAuthService;

    public UserController(UserService userService, UserAuthService userAuthService) {
        this.userService = userService;
        this.userAuthService = userAuthService;
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PatchMapping("/{id}")
    public UserResponse patchUser(@PathVariable Integer id, @RequestBody Map<String, Object> map) {
        return userService.applyUser(map, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
    }







}
