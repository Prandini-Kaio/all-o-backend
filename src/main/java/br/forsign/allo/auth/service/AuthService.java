package br.forsign.allo.auth.service;

import br.forsign.allo.auth.model.AuthInput;
import br.forsign.allo.auth.model.LoginInput;
import br.forsign.allo.config.TokenService;
import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.provedor.service.ProvedorService;
import br.forsign.allo.usuario.domain.Usuario;
import br.forsign.allo.usuario.repository.UsuarioRepository;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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

    public String login(LoginInput input){

        var usernamePass = new UsernamePasswordAuthenticationToken(input.getLogin(), input.getSenha());
        var auth = this.authenticationManager.authenticate(usernamePass);

        return tokenService.generateToken((Usuario) auth.getPrincipal());
    }

    public boolean register(AuthInput input) {
        if(loadUserByUsername(input.getLogin()) != null)
            return false;

        String encodedPassword = new BCryptPasswordEncoder().encode(input.getSenha());
        Usuario usuario = new Usuario(input.getLogin(), encodedPassword, input.getRole());

        this.usuarioRepository.save(usuario);

        return true;
    }

    public UserDetails loadUserByUsername(String username){
        return usuarioRepository.findByLogin(username);
    }
}
