package com.mithran.ticket.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mithran.ticket.entity.Role;
import com.mithran.ticket.entity.User;
import com.mithran.ticket.repository.RoleRepository;
import com.mithran.ticket.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerNewUser(User user) {

       user.setUserPassword(getEncodedPassword(user.getUserPassword()));
       Role role = roleRepository.findById("User").get();

       Set<Role> roles=new HashSet<Role>();
       user.setRoles(roles);
       
        return userRepository.save(user);
    }

    @Override
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }


    


    


}