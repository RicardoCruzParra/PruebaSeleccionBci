package com.evaluacion.creacionUsuarios.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtTokenProvider
{
    private final SecretKey secretKey;

    public JwtTokenProvider()
    {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Genera una clave segura
    }

    public String crearToken(String correo)
    {
        return Jwts.builder()
                .setSubject(correo)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día de expiración
                .signWith(secretKey) // Usa la clave segura generada
                .compact();
    }

    public Claims obtenerCorreo(String token)
    {
        return Jwts.parser().setSigningKey(token).build().parseSignedClaims(token).getBody();
    }

    public boolean validarToken(String token)
    {
        try
        {
            Jwts.parser().setSigningKey(token).build().parseSignedClaims(token).getBody();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public String resolverToken(HttpServletRequest req)
    {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer "))
        {
            return bearerToken.substring(7);
        }
        return null;
    }
}