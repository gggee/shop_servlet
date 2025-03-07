package com.example.sport_shop.service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${auth.secret}")
    private String secret;

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*20))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims=new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }
    public String decodeUsername(String token){
        return extractClaim(token).getSubject();
    }
    private Claims extractClaim(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    public Boolean validateToken(String token, UserDetails userDetails){
        String username=decodeUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public Boolean isTokenExpired(String token){
        return extractClaim(token).getExpiration().before(new Date());
    }
}

