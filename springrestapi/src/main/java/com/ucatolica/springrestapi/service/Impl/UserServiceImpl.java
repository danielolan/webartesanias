package com.ucatolica.springrestapi.service.Impl;

import com.ucatolica.springrestapi.model.User;
import com.ucatolica.springrestapi.repository.UserRepository;
import com.ucatolica.springrestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository eRepository;


    @Override
    public List<User> getUser(){
        return eRepository.findAll();
    }

    @Override
    public User saveUser(User userInfo) {
        return eRepository.save(userInfo);
    }

    @Override
    public User getSingleUser(Long id) {
        Optional<User> userInfo = eRepository.findById(id);
        if(userInfo.isPresent()) {
            return userInfo.get();
        }
        throw new RuntimeException("User not found by ID " +id);

    }

    @Override
    public void deleteUser(Long id) {

        eRepository.deleteById(id);

    }

    @Override
    public User updateUser(User userInfo) {
        return eRepository.save(userInfo);
    }
}
