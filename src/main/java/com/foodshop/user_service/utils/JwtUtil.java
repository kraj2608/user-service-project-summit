package com.foodshop.user_service.utils;

import com.foodshop.user_service.enums.TOKEN_TYPE;
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

    private TOKEN_TYPE tokenType;

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

    public boolean isTokenValid(String token, UserDetails userDetails,TOKEN_TYPE tokenType) {
        this.tokenType = tokenType;
        final String username = extractTokenUsername(token,tokenType);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
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
                .setExpiration(new Date(System.currentTimeMillis() + jwtRefreshExpirationMs))
                .signWith(getRefreshTokenSignInKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractTokenExpiration(token).before(new Date());
    }

    private Date extractTokenExpiration(String token) {
        return extractTokenClaim(token, Claims::getExpiration);
    }


    private  <T> T extractTokenClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractTokenAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractTokenAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(tokenType == TOKEN_TYPE.ACCESS_TOKEN
                        ? getAccessTokenSignInKey()
                        : getRefreshTokenSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public String extractTokenUsername(String token,TOKEN_TYPE tokenType) {
        this.tokenType = tokenType;
        return extractTokenClaim(token, Claims::getSubject);
    }


}
