package com.example.SpringSecurityWorkshopTwo.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {
    private String key = "";
    public JWTService() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGen.generateKey();
            key = Base64.getEncoder().encodeToString(secretKey.getEncoded());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
    public String generateToken(String username) {
        Map<String,Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
                .signWith(getKey())
                .compact();
    }

    private Key getKey() {
        byte[] bytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(bytes);
    }
}
