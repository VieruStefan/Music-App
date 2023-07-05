package com.pos.spotify.service;

import com.pos.spotify.userdetails.User;

import java.util.List;

public interface UserService {
    //create
    void saveUser(User user);

    //update
    void updateUser(User user);

    //read
    List<User> getAll();

    //read by id
    User findByUid(int id);
    User findByUname(String username);

    //delete
    void deleteUserById(int id);
}
