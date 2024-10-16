package br.forsign.allo.auth.service;

import br.forsign.allo.auth.model.AuthInput;
import br.forsign.allo.auth.model.LoginInput;
import br.forsign.allo.auth.model.LoginOutput;
import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.service.actions.ClienteGetter;
import br.forsign.allo.common.error.BusinessException;
import br.forsign.allo.config.TokenService;
import br.forsign.allo.entidade.domain.Entidade;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.service.action.ProvedorGetter;
import br.forsign.allo.usuario.domain.Usuario;
import br.forsign.allo.usuario.domain.UsuarioRole;
import br.forsign.allo.usuario.repository.UsuarioRepository;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@CommonsLog
public class AuthService {

    @Resource
    private UsuarioRepository usuarioRepository;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private ProvedorGetter provedorGetter;

    @Resource
    private ClienteGetter clienteGetter;

    @Resource
    private TokenService tokenService;

    public LoginOutput login(LoginInput input){

        String login = input.getLogin().toLowerCase();

        log.info("Tentativa de login: " + login);

        var usernamePass = new UsernamePasswordAuthenticationToken(login, input.getSenha());
        Authentication auth = this.authenticationManager.authenticate(usernamePass);

        String token = tokenService.generateToken((Usuario) auth.getPrincipal());

        Usuario usuario = (Usuario) auth.getPrincipal();

        log.info("Login bem sucedido: " + login);

        return createLoginOutput(token, usuario);
    }

    public UserDetails register(AuthInput input) {

        log.info("Tentativa de cadastro: " + input.getLogin());

        if(loadUserByUsername(input.getLogin()) != null)
            throw new BusinessException("Usúario já cadastrado.");

        String encodedPassword = new BCryptPasswordEncoder().encode(input.getSenha());
        Usuario usuario = new Usuario(input.getLogin().toLowerCase(), encodedPassword, input.getRole());

        log.info("Cadastro bem sucedido: " + input.getLogin());

        return this.usuarioRepository.save(usuario);
    }

    public UserDetails loadUserByUsername(String username){
        return usuarioRepository.findByLogin(username);
    }

    public static UserDetails getContextUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return (UserDetails) authentication.getPrincipal();
    }

    private LoginOutput createLoginOutput(String token, Usuario usuario){

        String nome = null;
        Long id = null;

        if(usuario.getRole().equals(UsuarioRole.CLIENTE)) {
            Cliente cliente = clienteGetter.byUsername(usuario.getLogin());
            id = cliente.getId();
            nome = cliente.getNome();
        }
        else if(usuario.getRole().equals(UsuarioRole.PROVEDOR)) {
            Provedor provedor = provedorGetter.byUsername(usuario.getLogin());
            id = provedor.getId();
            nome = provedor.getRazaoSocial();
        }


        return new LoginOutput(id, nome, usuario.getRole(), token);
    }
}
