package com.fid.demo.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Service
public class TokenManager {

    public Key key() {
        byte[] secret = Base64.getDecoder().decode("o40dCNjd8mmDN2+/nfHdIB2ZWta80foXqDx2rouL4nw");
        return Keys.hmacShaKeyFor(secret);
    }

    public String generateToken(String username) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("www.stockManagement.com")
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(5, ChronoUnit.MINUTES)))
                .signWith(key())
                .compact();
    }

    public boolean tokenValidate(String token) {
        return (getUserFromToken(token) != null && isExpired(token));
    }

    private boolean isExpired(String token) {
        Claims claims = Jwts.parser().setSigningKey(key()).parseClaimsJws(token).getBody();
        return claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    public String getUserFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(key()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public void test() {
        Instant now = Instant.now();
        byte[] secret = Base64.getDecoder().decode("o40dCNjd8mmDN2+/nfHdIB2ZWta80foXqDx2rouL4nw");

        String token = Jwts.builder()
                .setSubject("Fid")
                .setIssuer("www.stockManagement.com")
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.DAYS)))
                .signWith(Keys.hmacShaKeyFor(secret))
                .compact();

        System.out.println(token);
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(secret)).parseClaimsJws(token);
        System.out.println(claimsJws);

        System.out.println("{bcrypt}" + new BCryptPasswordEncoder().encode("secret"));
    }
}
