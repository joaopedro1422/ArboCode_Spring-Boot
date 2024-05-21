package com.example.springBoot.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.springBoot.models.ClienteModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Classe Service para a manipulação dos Tokens JWT. Possui um método para gerar um novo token e um método para validar
 * o token passado como parâmetro.
 */
@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(ClienteModel cliente){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("ArboCode_Spring-Boot")
                    .withSubject(cliente.getEmail())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro de autenticação");
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm= Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("ArboCode_Spring-Boot")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTVerificationException exception){
            return null;
        }
    }

    /**
     * Método auxiliar para definiar o tempo de validade do token de acesso do cliente, após esse tempo será necessário
     * que o login seja novamente realizado.
     * @return
     */
    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-3"));
    }
}
