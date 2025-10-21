package com.todolist.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.todolist.dao.UserDAO;
import com.todolist.entity.*;
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

    public UserServiceImplementation(UserDAO userDAO,  ObjectMapper objectMapper) {
        this.userDAO = userDAO;
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional
    public User createUser(User user) {
        return userDAO.createUser(user);
    }

    @Override
    public User findUserByNickName(String nickName) {
        return userDAO.findUserByNickName(nickName);
    }

    @Override
    public User findUserById(Integer id) {
        return userDAO.findUserById(id);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        return userDAO.updateUser(user);
    }


    @Override
    public User applyUser(Map<String, Object> map, User user) {
        try {
            String json = objectMapper.writeValueAsString(map);
            objectMapper.readerForUpdating(user).readValue(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
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

    @Override
    public UserAuth findUserAuthByUserId(Integer id) {
        return userDAO.findUserAuthByUserId(id);
    }

    @Override
    public List<Task> findIndividualTaskByUserId(Integer id) {
        return userDAO.findIndividualTaskByUserId(id);
    }

    @Override
    public List<Team> findTeamByUserId(Integer id) {
        return  userDAO.findTeamByUserId(id);
    }

    @Override
    public List<Task> findTeamTaskByUserId(Integer id) {
        return userDAO.findTeamTaskByUserId(id);
    }

    @Override
    public List<Message> findMessageByUserId(Integer id) {
        return userDAO.findMessageByUserId(id);
    }
}
