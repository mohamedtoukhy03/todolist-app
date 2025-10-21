package com.todolist.service;

import com.todolist.entity.UserAuth;

import java.util.Map;

public interface UserAuthService {
    public UserAuth createUserAuth(UserAuth userAuth);
    public UserAuth updateUserAuth(UserAuth userAuth);
    public UserAuth applyUserAuth(UserAuth userAuth, Map<String, Object> map);
    public void deleteUserAuth(UserAuth userAuth);
    public UserAuth findUserAuthById(Integer id);
}
