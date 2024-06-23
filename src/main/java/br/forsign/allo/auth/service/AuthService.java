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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private ProvedorService provedorService;

    @Resource
    private TokenService tokenService;

    public String login(LoginInput input){

        var usernamePass = new UsernamePasswordAuthenticationToken(input.getLogin(), input.getSenha());
        var auth = this.authenticationManager.authenticate(usernamePass);

        return tokenService.generateToken((Usuario) auth.getPrincipal());
    }

    public boolean register(AuthInput input) {
        if(loadCurrentUser() != null)
            return false;

        String encodedPassword = new BCryptPasswordEncoder().encode(input.getSenha());
        Usuario usuario = new Usuario(input.getLogin(), encodedPassword, input.getRole());

        this.usuarioRepository.save(usuario);

        this.provedorService.create(input.getProvedorInput());

        return true;
    }

    public UserDetails loadCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();

        return usuarioRepository.findByLogin(user.getUsername());
    }
}
