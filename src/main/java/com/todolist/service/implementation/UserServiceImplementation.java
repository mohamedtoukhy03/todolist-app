package com.todolist.service.implementation;

import com.todolist.dao.UserDAO;
import com.todolist.dto.request.UserRequest;
import com.todolist.dto.response.UserResponse;
import com.todolist.entity.*;
import com.todolist.mapper.UserMapper;
import com.todolist.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImplementation implements UserService {

    UserDAO  userDAO;
    UserMapper userMapper;

    public UserServiceImplementation(UserDAO userDAO, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        User u = userMapper.toUser(userRequest);
        UserAuth userAuth = userMapper.toAuth(userRequest);
        u.addUserAuth(userAuth);
        u = userDAO.createUser(u);
        return userMapper.toDTO(u, u.getUserAuth());

    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findUserByNickName(String nickName) {
        User u =  userDAO.findUserByNickName(nickName);
        return userMapper.toDTO(u, u.getUserAuth());
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findUserById(Integer id) {
        User u = userDAO.findUserById(id);
        return userMapper.toDTO(u, u.getUserAuth());
    }

    @Override
    @Transactional
    public UserResponse updateUser(Integer userId, UserRequest userRequest) {
        userDAO.findUserById(userId);
        User tempUser =  userMapper.toUser(userRequest);
        UserAuth userAuth = userMapper.toAuth(userRequest);
        userAuth.setUserId(userId);
        tempUser.setUserId(userId);
        tempUser.addUserAuth(userAuth);
        User user = userDAO.updateUser(tempUser);
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

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findUsersByTeamId(Integer teamId) {
        List<User> users = userDAO.findUsersByTeamId(teamId);
        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach(user -> userResponses.add(userMapper.toDTO(user, user.getUserAuth())));
        return userResponses;
    }
}
