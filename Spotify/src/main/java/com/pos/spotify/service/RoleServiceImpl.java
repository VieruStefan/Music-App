package com.pos.spotify.service;

import com.pos.spotify.userdetails.Role;
import com.pos.spotify.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;
    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void updateRole(Role role) {
        int rid = role.getRid();
        Role roleDB = roleRepository.findById(rid).get();
        if(Objects.nonNull(role.getRname()) && !"".equals(roleDB.getRname())){
            roleDB.setRname(role.getRname());
        }
        roleRepository.save(roleDB);
    }

    @Override
    public List<Role> getAll() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public Role findByRid(int rid) {
        return roleRepository.findById(rid).get();
    }

    @Override
    public void deleteRoleById(int id) {
        roleRepository.deleteById(id);
    }
}
