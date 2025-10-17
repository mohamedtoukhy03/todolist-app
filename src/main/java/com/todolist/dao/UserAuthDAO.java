package com.todolist.dao;

import com.todolist.entity.UserAuth;

public interface UserAuthDAO {
    // CRUD features
    public UserAuth createUserAuth(UserAuth userAuth);
    public UserAuth updateUserAuth(UserAuth userAuth);
    public void deleteUserAuth(UserAuth userAuth);
    public UserAuth findUserAuthById(Integer id);
}
