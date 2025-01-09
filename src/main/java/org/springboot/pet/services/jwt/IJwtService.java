package org.springboot.pet.services.jwt;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

public interface IJwtService {
    public String generateToken(String userEmail);

    public String extractUsername(String token);

    public Long extractUserIdFromToken(String token);

    public Date extractExpirationTimeFromToken(String token);

    public <T> T extractClaimsFromToken(String token, Function<String, T> claimResolver);

    public Boolean validateToken(String token, UserDetails getUserDto);
}
