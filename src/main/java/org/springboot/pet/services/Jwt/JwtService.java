package org.springboot.pet.services.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    public String generateToken(Long userId);

    public String extractUsername(String token);

    public Long extractUserIdFromToken(String token);

    public Date extractExpirationTimeFromToken(String token);

    public <T> T extractClaimsFromToken(String token, Function<String, T> claimResolver);

    public Boolean validateToken(String token, UserDetails userDetails);
}
