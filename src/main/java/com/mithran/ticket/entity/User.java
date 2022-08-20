package com.mithran.ticket.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.agent.builder.AgentBuilder.PoolStrategy.Eager;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {


    @Id
    private String username;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",joinColumns = {
        @JoinColumn(name = "USER_ID")
    },
    inverseJoinColumns = {
        @JoinColumn(name = "ROLE_ID")
    })
    private Set<Role> roles;
    
}
