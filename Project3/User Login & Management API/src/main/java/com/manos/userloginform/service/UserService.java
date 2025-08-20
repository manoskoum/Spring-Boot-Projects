package com.manos.userloginform.service;

import com.manos.userloginform.model.User;
import com.manos.userloginform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user,Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User saveUser = optionalUser.get();
            saveUser.setFirstName(user.getFirstName());
            saveUser.setLastName(user.getLastName());
            saveUser.setEmail(user.getEmail());
            return userRepository.save(saveUser);
        } else {
            return null;
        }
    }

    public boolean deleteUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }else {
            return false;
        }
    }



    }
