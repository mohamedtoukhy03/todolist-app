package com.todolist.controller;

import com.todolist.dto.UserDTO;
import com.todolist.entity.User;
import com.todolist.mapper.Mapper;
import com.todolist.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    Mapper mapper;
    UserService userService;

    public UserController(UserService userService,  Mapper mapper) {
        this.mapper = mapper;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Integer id) {
        User user = userService.findUserById(id);
       return mapper.toDTO(user);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        User user = userService.createUser(mapper.toUser(userDTO));
        return mapper.toDTO(user);
    }

    @PutMapping
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        User user = userService.updateUser(mapper.toUser(userDTO));
        return mapper.toDTO(user);
    }

    @PatchMapping("/{id}")
    public UserDTO patchUser(@PathVariable Integer id, @RequestBody Map<String, Object> map) {
        User user = userService.updateUser(id, map);
        return mapper.toDTO(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
    }







}
