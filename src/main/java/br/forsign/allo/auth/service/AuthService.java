package br.forsign.allo.auth.service;

import br.forsign.allo.auth.model.AuthInput;
import br.forsign.allo.auth.model.RegisterInput;
import br.forsign.allo.config.TokenService;
import br.forsign.allo.usuario.domain.Usuario;
import br.forsign.allo.usuario.repository.UsuarioRepository;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author kaiooliveira
 * created 22/06/2024
 */

@Service
public class AuthService {

    @Resource
    private UsuarioRepository usuarioRepository;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private TokenService tokenService;

    public String login(AuthInput input){

        var usernamePass = new UsernamePasswordAuthenticationToken(input.getLogin(), input.getSenha());
        var auth = this.authenticationManager.authenticate(usernamePass);

        return tokenService.generateToken((Usuario) auth.getPrincipal());
    }

    public boolean register(RegisterInput input) {
        if(usuarioRepository.findByLogin(input.login()) != null)
            return false;

        String encodedPassword = new BCryptPasswordEncoder().encode(input.senha());
        Usuario usuario = new Usuario(input.login(), encodedPassword, input.role());

        this.usuarioRepository.save(usuario);

        return true;
    }
}
