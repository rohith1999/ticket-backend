package com.mithran.ticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mithran.ticket.dto.RoleDTO;
import com.mithran.ticket.entity.Role;
import com.mithran.ticket.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role createNewRole(RoleDTO roleDto) {
        
     return roleRepository.save(new Role(roleDto.getRoleName(),roleDto.getRoleDescription()));
     
    }
    
}
