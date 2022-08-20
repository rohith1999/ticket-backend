package com.mithran.ticket.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mithran.ticket.entity.JwtRequest;
import com.mithran.ticket.entity.JwtResponse;
import com.mithran.ticket.entity.User;
import com.mithran.ticket.repository.UserRepository;
import com.mithran.ticket.util.JwtUtil;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {

        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();

        authenticate(userName, userPassword);

       final UserDetails userDetails = loadUserByUsername(userName);

       String newGeneratedToken = jwtUtil.generateToken(userDetails);

       User user = userRepository.findById(userName).get();

       return new JwtResponse(user,newGeneratedToken);


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findById(username).get();
        if (user != null) {

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getUserPassword(), getAuthorities(user));

        } else {

            throw new UsernameNotFoundException("UserName is not valid");

        }

    }

    private Set getAuthorities(User user) {

        Set authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });

        return authorities;

    }

    private void authenticate(String userName, String userPassword) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {

            throw new Exception("User is Disabled");

        } catch (BadCredentialsException e) {

            throw new Exception("Bad credentials for user");

        }

    }

}
