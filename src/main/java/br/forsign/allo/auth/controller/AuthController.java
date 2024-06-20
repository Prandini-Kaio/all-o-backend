package br.forsign.allo.auth.controller;

import br.forsign.allo.auth.model.AuthInput;
import br.forsign.allo.auth.model.RegisterInput;
import br.forsign.allo.config.TokenService;
import br.forsign.allo.usuario.domain.Usuario;
import br.forsign.allo.usuario.repository.UsuarioRepository;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kaiooliveira
 * created 19/06/2024
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UsuarioRepository repository;

    @Resource
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthInput input) {

        var usernamePass = new UsernamePasswordAuthenticationToken(input.getLogin(), input.getSenha());
        var auth = this.authenticationManager.authenticate(usernamePass);

        String token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterInput input) {

        if(repository.findByLogin(input.login()) != null)
            return ResponseEntity.badRequest().build();

        String encodedPassword = new BCryptPasswordEncoder().encode(input.senha());
        Usuario usuario = new Usuario(input.login(), encodedPassword, input.role());

        this.repository.save(usuario);

        return ResponseEntity.ok().build();
    }
}
