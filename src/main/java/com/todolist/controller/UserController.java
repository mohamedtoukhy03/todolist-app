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
    UserMapper mapper;
    UserService userService;
    UserAuthService userAuthService;

    public UserController(UserService userService, UserAuthService userAuthService, UserMapper mapper) {
        this.mapper = mapper;
        this.userService = userService;
        this.userAuthService = userAuthService;
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Integer id) {
        User user = userService.findUserById(id);
        UserAuth userAuth = userAuthService.findUserAuthById(id);
        return mapper.toDTO(user, userAuth);
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        User user = mapper.toUser(userRequest);
        UserAuth userAuth = mapper.toAuth(userRequest);
        user.addUserAuth(userAuth);
        user = userService.createUser(user);
        return mapper.toDTO(user, userAuth);
    }

    @PatchMapping("/{id}")
    public UserResponse patchUser(@PathVariable Integer id, @RequestBody Map<String, Object> map) {
        User u = userService.findUserById(id);
        User user = userService.applyUser(map, u);
        UserAuth userAuth = userAuthService.applyUserAuth(userAuthService.findUserAuthById(id), map);
        user = userService.updateUser(user);
        return mapper.toDTO(user, userAuth);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
    }







}
