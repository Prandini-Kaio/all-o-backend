package br.forsign.allo.auth.service.action;

import br.forsign.allo.auth.domain.PasswordResetToken;
import br.forsign.allo.auth.repository.PasswordResetTokenRepository;
import br.forsign.allo.common.utils.CommonExceptionSupplier;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author kaiooliveira
 * created 01/07/2024
 */

@Component
public class PasswordResetTokenGetter {

    @Resource
    private PasswordResetTokenRepository repository;

    public PasswordResetToken findByToken(String token) {
        return this.repository.findByToken(token).orElseThrow(CommonExceptionSupplier.naoEncontrado("Token"));
    }
}
