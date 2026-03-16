package com.barbearia.aplicacao.security;

import com.barbearia.aplicacao.model.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String gerarToken(Usuario usuario) {
        return Jwts.builder()
                .subject(usuario.getEmail())
                .claim("role", usuario.getRole().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h
                .signWith(getSecretKey())
                .compact();
    }

    public String extrairEmail(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean tokenValido(String token) {
        try {
            extrairEmail(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
