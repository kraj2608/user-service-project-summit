package com.foodshop.user_service.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Value("${jwt.jwtSecret}")
    private String jwtAccesSecret;


    @Value("${jwt.refreshSecret}")
    private String jwtAccessRefreshSecret;

    @Value("${jwt.jwtExpirationMs}")
    private int jwtAccessExpirationMs;

    @Value("${jwt.refreshExpirationMs}")
    private int jwtRefreshExpirationMs;

    public String generateAccessToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return createAccessToken(claims, userDetails.getUsername());
    }

    public String generateRefreshToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return createRefreshToken(claims, userDetails.getUsername());
    }

    private Key getAccessTokenSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.jwtAccesSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Key getRefreshTokenSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.jwtAccessRefreshSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String createAccessToken(Map<String,Object> claims, String subject){
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtAccessExpirationMs))
                .signWith(getAccessTokenSignInKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    private String createRefreshToken(Map<String,Object> claims, String subject){
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtAccessExpirationMs))
                .signWith(getRefreshTokenSignInKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getAccessTokenSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
}
