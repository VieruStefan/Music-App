package com.pos.spotify.service;

import com.pos.spotify.userdetails.User;
import com.pos.spotify.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        int uid = user.getUid();
        User userDB = userRepository.findById(uid).get();
        if(Objects.nonNull(user.getUname()) && !"".equals(userDB.getUname()) && userDB.getUpass().equals(user.getUname())){
            if(!userDB.getUpass().equals(user.getUpass()))
            {
                userDB.setUpass(user.getUpass());
            }
        }

        userRepository.save(userDB);
    }


    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findByUid(int id) {
        return userRepository.findById(id).get();
    }
    @Override
    public User findByUname(String username) {
        List<User> userList = getAll();
        for (User user:
             userList) {
            if(user.getUname().equals(username))
                return user;
        }
        return null;
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
