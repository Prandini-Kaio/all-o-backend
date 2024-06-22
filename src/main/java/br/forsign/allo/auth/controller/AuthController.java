package br.forsign.allo.auth.controller;

import br.forsign.allo.auth.model.AuthInput;
import br.forsign.allo.auth.model.RegisterInput;
import br.forsign.allo.auth.service.AuthService;
import br.forsign.allo.config.TokenService;
import br.forsign.allo.usuario.domain.Usuario;
import br.forsign.allo.usuario.repository.UsuarioRepository;
import br.forsign.allo.usuario.service.UsuarioService;
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
    private AuthService service;

    @Resource
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthInput input) {
        return ResponseEntity.ok().body(this.service.login(input));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterInput input) {

        this.service.register(input);

        return ResponseEntity.ok().build();
    }
}
