package com.todolist.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.todolist.dao.UserDAO;
import com.todolist.dto.request.UserRequest;
import com.todolist.dto.response.UserResponse;
import com.todolist.entity.*;
import com.todolist.mapper.UserMapper;
import com.todolist.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImplementation implements UserService {

    UserDAO  userDAO;
    ObjectMapper objectMapper;
    UserMapper userMapper;

    public UserServiceImplementation(UserDAO userDAO,  ObjectMapper objectMapper, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userDAO = userDAO;
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        User u = userMapper.toUser(userRequest);
        u = userDAO.createUser(u);
        return userMapper.toDTO(u, u.getUserAuth());

    }

    @Override
    public UserResponse findUserByNickName(String nickName) {
        User u =  userDAO.findUserByNickName(nickName);
        return userMapper.toDTO(u, u.getUserAuth());
    }

    @Override
    public UserResponse findUserById(Integer id) {
        User u = userDAO.findUserById(id);
        return userMapper.toDTO(u, u.getUserAuth());
    }

    @Override
    @Transactional
    public UserResponse updateUser(UserRequest userRequest) {
        User u = userMapper.toUser(userRequest);
        u = userDAO.updateUser(u);
        return userMapper.toDTO(u, u.getUserAuth());
    }


    @Override
    @Transactional
    public UserResponse applyUser(Map<String, Object> map, Integer id) {
        User user = userDAO.findUserById(id);
        try {
            String json = objectMapper.writeValueAsString(map);
            objectMapper.readerForUpdating(user).readValue(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        user = userDAO.updateUser(user);
        return userMapper.toDTO(user, user.getUserAuth());
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        userDAO.deleteUserById(id);
    }

    @Override
    @Transactional
    public void deleteUserByNickName(String nickName) {
        userDAO.deleteUserByNickName(nickName);
    }
}
