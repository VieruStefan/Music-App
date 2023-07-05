package com.pos.spotify.service;

import com.pos.spotify.userdetails.Role;

import java.util.List;

public interface RoleService {
    //create
    void saveRole(Role role);

    //update
    void updateRole(Role role);

    //read
    List<Role> getAll();

    //read by id
    Role findByRid(int id);

    //delete
    void deleteRoleById(int id);
}
