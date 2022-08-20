package com.mithran.ticket.service;

import com.mithran.ticket.entity.User;


public interface UserService {

    public User registerNewUser(User user);
    public String getEncodedPassword(String password);
    
}
