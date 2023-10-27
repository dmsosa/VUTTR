package com.duvi.vuttr.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.duvi.vuttr.domain.user.User;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("vuttr")
                    .withSubject(user.getUsername())
                    .withClaim("email", user.getEmail())
                    .withExpiresAt(generateExpDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException ex) {
            throw new RuntimeException("Error while generating token", ex);
        }
    }

    public String validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return JWT.require(algorithm)
                    .withIssuer("vuttr")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex) {
            return "";
        }
    }
    private Instant generateExpDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("00:00"));
    }
}
