package com.todolist.service;

import com.todolist.dto.request.UserRequest;
import com.todolist.dto.response.UserResponse;
import com.todolist.entity.UserAuth;

import java.util.Map;

public interface UserAuthService {
    public UserResponse createUserAuth(UserRequest userRequest);
    public UserResponse updateUserAuth(UserRequest userRequest);
    public UserResponse applyUserAuth(Integer id, Map<String, Object> map);
    public void deleteUserAuth(Integer id);
    public UserResponse findUserAuthById(Integer id);
}
