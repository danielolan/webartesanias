package com.ucatolica.springrestapi.controller;

import com.ucatolica.springrestapi.model.User;
import com.ucatolica.springrestapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService eService;

    @GetMapping("/user")
    public List<User> getUser() {
        return eService.getUser();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return eService.getSingleUser(id);
    }

    @PostMapping("/user")
    public User saveUser (@Valid @RequestBody User User) {
        return eService.saveUser(User);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User User) {
        User.setUser_id(id);
        return eService.updateUser(User);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam("id") Long id) {
        eService.deleteUser(id);
    }
}
