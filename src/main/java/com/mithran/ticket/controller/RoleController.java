package com.mithran.ticket.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mithran.ticket.dto.RoleDTO;
import com.mithran.ticket.entity.Role;
import com.mithran.ticket.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public Role createNewRole(@RequestBody RoleDTO role) {

        return roleService.createNewRole(role);
    }
    
    




    
}
