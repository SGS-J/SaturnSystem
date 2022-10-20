package com.SGSJ.Saturn.rest_controller;

import com.SGSJ.Saturn.domain.User.User;
import com.SGSJ.Saturn.domain.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public User addNewUser(@RequestBody User user) {
        return userService.add(user);
    }
}
