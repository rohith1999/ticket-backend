package com.mithran.ticket.service;

import org.springframework.stereotype.Service;

import com.mithran.ticket.dto.RoleDTO;
import com.mithran.ticket.entity.Role;


public interface RoleService {

    public Role createNewRole(RoleDTO role);


    
}
