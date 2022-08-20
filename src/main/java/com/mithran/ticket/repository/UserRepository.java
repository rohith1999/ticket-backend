package com.mithran.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mithran.ticket.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    

    
}
