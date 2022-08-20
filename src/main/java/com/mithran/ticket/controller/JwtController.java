package com.mithran.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mithran.ticket.entity.JwtRequest;
import com.mithran.ticket.entity.JwtResponse;
import com.mithran.ticket.service.JwtService;

@RestController
@CrossOrigin
@RequestMapping("/jwt")
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{

        return jwtService.createJwtToken(jwtRequest);
        


    }




    
}
