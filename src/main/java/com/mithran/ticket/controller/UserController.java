package com.mithran.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mithran.ticket.entity.User;
import com.mithran.ticket.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/create")
    public User registerNewUser(@RequestBody User user){

       return userService.registerNewUser(user);

    }

    @GetMapping(value="/for-admin")
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin() {
        return "This URL is only accessible to admin";
    }

    @GetMapping(value="/for-user")
    @PreAuthorize("hasRole('User')")
    public String forUser() {
        return "This URL is only accessible to the user";
    }

    @GetMapping(value="/get-count")
    @PreAuthorize("hasRole('Admin')")
    public long getUserCount(){
        return userService.getUserCount();
    }
    

    
    
    
}
