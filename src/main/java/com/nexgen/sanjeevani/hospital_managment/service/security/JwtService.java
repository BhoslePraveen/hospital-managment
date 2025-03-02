package com.nexgen.sanjeevani.hospital_managment.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@Slf4j
public class JwtService {
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.token.expiration.time}")
    private long tokenExpirationTime;

    //Generate the Token
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(Instant.now().getEpochSecond() + tokenExpirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Extract the claims
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // extract user-name from token
    public String extractUserName(String token) {
        return extractClaims(token).getSubject();
    }

    // check if token is expired
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // check if the token is valid
    public boolean validateToken(String token, UserDetails userDetails) {
        return extractUserName(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

}
