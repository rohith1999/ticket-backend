package com.mithran.ticket.service;

import org.springframework.stereotype.Service;

import com.mithran.ticket.dto.RoleDto;
import com.mithran.ticket.entity.Role;


public interface RoleService {

    public Role createNewRole(RoleDto role);


    
}
