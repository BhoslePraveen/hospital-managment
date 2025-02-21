package com.nexgen.sanjeevani.hospital_managment.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JwtService {
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.token.expiration.time}")
    private long tokenExpirationTime;

    //Generate the Token
    public String generateToken(Map<String, Object> claims, UserDetails userDetails ,long tokenExpirationTime){
       return Jwts.builder()
               .setClaims(claims)
               .setSubject(userDetails.getUsername())
               .setIssuedAt(java.util.Date.from(java.time.Instant.now()))
               .setExpiration(java.util.Date.from(java.time.Instant.now().plusMillis(tokenExpirationTime)))
               .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512,secretKey)
               .compact();
    }

    //Get the expiry time
    public Date getTokenExpirationTime(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    //Check for Expiry for time
    public boolean isTokenExpired(String token){
        return getTokenExpirationTime(token).before(new Date());
    }

    //Check for valid token
    public boolean validateToken(String token, UserDetails userDetails){
        final String username = extractClaims(token, Claims::getSubject);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    //Extract claims
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    public Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
