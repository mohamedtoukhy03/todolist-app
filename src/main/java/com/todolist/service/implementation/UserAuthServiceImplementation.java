package com.todolist.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.todolist.dao.UserAuthDAO;
import com.todolist.entity.UserAuth;
import com.todolist.service.UserAuthService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class UserAuthServiceImplementation implements UserAuthService {
     UserAuthDAO userAuthDAO;
     ObjectMapper objectMapper;

    public UserAuthServiceImplementation(UserAuthDAO userAuthDAO,  ObjectMapper objectMapper) {
        this.userAuthDAO = userAuthDAO;
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional
    public UserAuth createUserAuth(UserAuth userAuth) {
        return userAuthDAO.createUserAuth(userAuth);
    }

    @Override
    @Transactional
    public UserAuth updateUserAuth(UserAuth userAuth) {
        return userAuthDAO.updateUserAuth(userAuth);
    }


    @Override
    public UserAuth applyUserAuth(UserAuth userAuth, Map<String, Object> map) {
        try {
            String json = objectMapper.writeValueAsString(map);
            objectMapper.readerForUpdating(userAuth).readValue(json);
        } catch (IOException e) {
            throw new RuntimeException("Failed to apply patch to UserAuth", e);
        }
        return userAuth;
    }


    @Override
    @Transactional
    public void deleteUserAuth(UserAuth userAuth) {
        userAuthDAO.deleteUserAuth(userAuth);
    }

    @Override
    public UserAuth findUserAuthById(Integer id) {
        return  userAuthDAO.findUserAuthById(id);
    }
}
