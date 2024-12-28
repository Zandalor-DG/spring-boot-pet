package org.springboot.pet.services.Jwt.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springboot.pet.services.Jwt.JwtService;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtServiceImpl implements JwtService {
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    private String createToken(Map<String, Object> claims, Long userId) {
        return Jwts.builder()
                .claims(claims)
                .subject(String.valueOf(userId))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 5))
                .signWith(getSignInKey())
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] bytes = Base64.getDecoder().decode(SECRET.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(bytes, "HmacSHA256");
    }

    private Boolean isTokenExpired(String token) {
        return extractExpirationTimeFromToken(token).before(new Date());
    }

    @Override
    public String generateToken(Long userId) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userId);
    }

    @Override
    public <T> T extractClaimsFromToken(String token, Function<String, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims.getSubject());
    }

    @Override
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    @Override
    public Long extractUserIdFromToken(String token) {
        return extractAllClaims(token).get("id", Long.class);
    }

    @Override
    public Date extractExpirationTimeFromToken(String token) {
        return extractAllClaims(token).getExpiration();
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
