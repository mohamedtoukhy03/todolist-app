package com.todolist.dao;


import com.todolist.entity.*;

import java.util.List;

public interface UserDAO {
    // CRUD features
    public User createUser(User user);
    public User findUserByNickName(String nickName);
    public User findUserById(Integer id);
    public User updateUser(User user);
    public void deleteUserById(Integer id);
    public void deleteUserByNickName(String nickName);
    public List<User> findUsersByTeamId(Integer teamId);
}
