package com.mithran.ticket.util;

import java.security.Signature;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.security.auth.Subject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mithran.ticket.constant.PropertyConstant;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {


    public String getUserNameFromToken(String token){

        return getClaimFromToken(token, Claims::getSubject);

    }

    /**
     * getClaimFromToken is an higerOrder function. A function which returns or accepts a function is called higer order function
     * @param <T> is a generic datatype which accepts any value 
     * @param token 
     * @param claimResolver
     * @return <T> is a generic datatype which accepts any value
     */
    public <T> T getClaimFromToken(String token, Function<Claims,T> claimResolver){

        final Claims claims = getAllClaimsFromToken(token);
        return claimResolver.apply(claims);

    }

    private Claims getAllClaimsFromToken(String token) {

        return Jwts.parser().setSigningKey(PropertyConstant.SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails){

        String userName = getUserNameFromToken(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){

        final Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());



    }

    private Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);

    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<String,Object>();

        return Jwts.builder().setClaims(claims)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setSubject(userDetails.getUsername())
        .setExpiration(new Date(System.currentTimeMillis() + PropertyConstant.TOKEN_VALIDITY*1000))
        .signWith(SignatureAlgorithm.HS512,PropertyConstant.SECRET_KEY)
        .compact();

    }

    
}
