package br.forsign.allo.auth.service.action;

import br.forsign.allo.auth.domain.PasswordResetToken;
import br.forsign.allo.auth.repository.PasswordResetTokenRepository;
import br.forsign.allo.usuario.domain.Usuario;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author kaiooliveira
 * created 01/07/2024
 */

@Component
public class PasswordResetCreator {

    @Resource
    private PasswordResetTokenRepository repository;

    public void createPasswordReset(Usuario usuario, String token){
        PasswordResetToken tokien = new PasswordResetToken(usuario, token);
        repository.save(tokien);
    }
}
