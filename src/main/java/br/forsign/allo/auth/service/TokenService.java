package br.forsign.allo.auth.service;

import br.forsign.allo.auth.domain.PasswordResetToken;
import br.forsign.allo.auth.service.action.PasswordResetTokenGetter;
import br.forsign.allo.usuario.domain.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author kaiooliveira
 * created 20/06/2024
 */

@Service
@CommonsLog
public class TokenService {

    @Value("${allo.api.security.token.secret}")
    private String secret;

    @Resource
    private PasswordResetTokenGetter passwordResetTokenGetter;

    public String generateToken(Usuario usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("allo-backend")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token");
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("allo-backend")
                    .build();

            return verifier.verify(token).getSubject();
        }catch (JWTVerificationException e){
            log.error("Validação do token falhou, retornando vazio");
            return "";
        }
    }

    public String validatePasswordResetToken(String token){
        final PasswordResetToken tokien = passwordResetTokenGetter.findByToken(token);

        return !isTokenFound(tokien) ? "invalidToken"
                : isTokenExpired(tokien) ? "expiredToken"
                : null;
    }

    private boolean isTokenFound(PasswordResetToken tokien){
        return tokien != null;
    }

    private boolean isTokenExpired(PasswordResetToken token){
        final LocalDateTime dateTime = LocalDateTime.now();
        return token.getDataExpiracao().isBefore(dateTime);
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusDays(5).toInstant(ZoneOffset.of("-03:00"));
    }
}
