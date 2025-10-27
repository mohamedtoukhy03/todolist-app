package com.todolist.service;

import com.todolist.dto.request.UserRequest;
import com.todolist.dto.response.UserResponse;
import com.todolist.entity.*;

import java.util.List;
import java.util.Map;


public interface UserService {
    public UserResponse createUser(UserRequest userRequest);
    public UserResponse findUserByNickName(String nickName);
    public UserResponse findUserById(Integer id);
    public UserResponse updateUser(UserRequest userRequest);
    public UserResponse applyUser(Map<String, Object> map, Integer id);
    public void deleteUserById(Integer id);
    public void deleteUserByNickName(String nickName);

}
