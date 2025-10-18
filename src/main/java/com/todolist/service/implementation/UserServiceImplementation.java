package com.todolist.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.todolist.dao.UserDAO;
import com.todolist.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public User updateUser(Integer id, Map<String, Object> map) {
        if (map.containsKey("id"))
            throw new RuntimeException("id must be empty");
        User user = apply(map, userDAO.findUserById(id));
        return userDAO.updateUser(user);
    }

    private User apply(Map<String, Object> map, User user) {
        ObjectNode x = objectMapper.convertValue(user, ObjectNode.class);
        ObjectNode y = objectMapper.convertValue(map, ObjectNode.class);
        x.setAll(y);
        return objectMapper.convertValue(x, User.class);
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
