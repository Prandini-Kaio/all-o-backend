package br.forsign.allo.auth.service;

import br.forsign.allo.auth.model.AuthInput;
import br.forsign.allo.auth.model.LoginInput;
import br.forsign.allo.auth.model.LoginOutput;
import br.forsign.allo.auth.service.action.PasswordResetCreator;
import br.forsign.allo.auth.service.action.PasswordResetTokenGetter;
import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.service.actions.ClienteGetter;
import br.forsign.allo.common.error.BusinessException;
import br.forsign.allo.mail.service.EmailService;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.service.action.ProvedorGetter;
import br.forsign.allo.usuario.domain.Usuario;
import br.forsign.allo.usuario.domain.UsuarioRole;
import br.forsign.allo.usuario.repository.UsuarioRepository;
import br.forsign.allo.usuario.service.UsuarioService;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

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
    private PasswordResetCreator resetPasswordCreator;

    @Resource
    private PasswordResetTokenGetter passwordResetTokenGetter;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private ProvedorGetter provedorGetter;

    @Resource
    private ClienteGetter clienteGetter;

    @Resource
    private TokenService tokenService;

    @Resource
    private EmailService emailService;

    @Resource
    private UsuarioService usuarioService;

    public LoginOutput login(LoginInput input){

        String login = input.getLogin().toLowerCase();

        log.info("Tentativa de Login: " + login);

        var usernamePass = new UsernamePasswordAuthenticationToken(login, input.getSenha());
        Authentication auth = this.authenticationManager.authenticate(usernamePass);

        String token = tokenService.generateToken((Usuario) auth.getPrincipal());

        Usuario usuario = (Usuario) auth.getPrincipal();

        return createLoginOutput(token, usuario);
    }

    public UserDetails register(AuthInput input) {

        log.info("Tentativa de registro: " + input.getLogin());

        if(usuarioService.loadUserByUsername(input.getLogin()) != null)
            throw new BusinessException("Usúario já cadastrado.");

        String encodedPassword = new BCryptPasswordEncoder().encode(input.getSenha());
        Usuario usuario = new Usuario(input.getLogin().toLowerCase(), encodedPassword, input.getRole());

        emailService.send(
                input.getLogin(),
                "ALL-O. Seja bem-vindo!",
                "ALLO! Agradecemos pelo cadastro em nossa plataforma.\nEstamos aqui pra lhe ajudar em qualquer coisa.\n\nSeja bem-vindo!"
        );

        return this.usuarioService.create(usuario);
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

    public void forgotPassword(String email) {
        Usuario usuario = (Usuario) usuarioService.loadUserByUsername(email);

        if(usuario == null)
            throw new BusinessException("Usuário não encontrado.");

        String token = UUID.randomUUID().toString();
        resetPasswordCreator.createPasswordReset(usuario, token);

        this.emailService.send(emailService
                .generateResetPassEmail("http://localhost:8080", Locale.getDefault(), token, usuario));
    }

    public void validateResetPassToken(String token) {
        String result = tokenService.validatePasswordResetToken(token);

        if(result != null)
            throw new BusinessException(result);
    }

    public void changePassword(String token, String password) {
        this.validateResetPassToken(token);

        Usuario usuario = passwordResetTokenGetter.findByToken(token).getUsuario();

        String encodedPassword = new BCryptPasswordEncoder().encode(password);

        usuario.setSenha(encodedPassword);

        this.usuarioRepository.save(usuario);
    }
}
