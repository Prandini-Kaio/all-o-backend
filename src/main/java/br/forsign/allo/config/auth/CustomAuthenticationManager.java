package br.forsign.allo.config.auth;

import br.forsign.allo.usuario.domain.Usuario;
import br.forsign.allo.usuario.repository.UsuarioRepository;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author kaiooliveira
 * created 20/06/2024
 */
public class CustomAuthenticationManager implements AuthenticationManager {

    @Resource
    private UsuarioRepository usuarioRepository;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        try {
            UserDetails user = usuarioRepository.findByLogin(auth.getName());

            if (auth.getCredentials().toString().equals(user.getPassword())) {
                return auth;
            }
        } catch (NullPointerException e){
            throw new BadCredentialsException("Usuário não cadastrado!");
        }
        throw new BadCredentialsException("Senha incorreta");
    }
}
