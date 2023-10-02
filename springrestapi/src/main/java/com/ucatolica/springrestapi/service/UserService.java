package com.ucatolica.springrestapi.service;

import com.ucatolica.springrestapi.model.User;

import java.util.List;

public interface UserService {
    List<User> getUser();

    User saveUser(User user);

    User getSingleUser (Long id);

    void deleteUser (Long id);

    User updateUser (User user);
}
