package com.todolist.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.todolist.dao.UserAuthDAO;
import com.todolist.dto.request.UserRequest;
import com.todolist.dto.response.UserResponse;
import com.todolist.entity.UserAuth;
import com.todolist.mapper.UserMapper;
import com.todolist.service.UserAuthService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class UserAuthServiceImplementation implements UserAuthService {
     UserAuthDAO userAuthDAO;
     ObjectMapper objectMapper;
     UserMapper userMapper;

    public UserAuthServiceImplementation(UserAuthDAO userAuthDAO,  ObjectMapper objectMapper, UserMapper userMapper) {
        this.userAuthDAO = userAuthDAO;
        this.objectMapper = objectMapper;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserResponse createUserAuth(UserRequest userRequest) {
        UserAuth userAuth = userMapper.toAuth(userRequest);
        userAuth =  userAuthDAO.createUserAuth(userAuth);
        return userMapper.toDTO(userAuth.getUser(), userAuth);
    }

    @Override
    @Transactional
    public UserResponse updateUserAuth(UserRequest userRequest) {
        UserAuth userAuth = userMapper.toAuth(userRequest);
        userAuth =  userAuthDAO.updateUserAuth(userAuth);
        return userMapper.toDTO(userAuth.getUser(), userAuth);
    }


    @Override
    @Transactional
    public UserResponse applyUserAuth(Integer id, Map<String, Object> map) {
        UserAuth userAuth = userAuthDAO.findUserAuthById(id);
        try {
            String json = objectMapper.writeValueAsString(map);
            objectMapper.readerForUpdating(userAuth).readValue(json);
        } catch (IOException e) {
            throw new RuntimeException("Failed to apply patch to UserAuth", e);
        }
        userAuth = userAuthDAO.updateUserAuth(userAuth);
        return userMapper.toDTO(userAuth.getUser(), userAuth);
    }


    @Override
    @Transactional
    public void deleteUserAuth(Integer id) {
        userAuthDAO.deleteUserAuth(id);
    }

    @Override
    public UserResponse findUserAuthById(Integer id) {
        UserAuth userAuth = userAuthDAO.findUserAuthById(id);
        return userMapper.toDTO(userAuth.getUser(), userAuth);
    }
}
