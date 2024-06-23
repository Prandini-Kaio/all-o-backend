package br.forsign.allo.auth.controller;

import br.forsign.allo.auth.model.AuthInput;
import br.forsign.allo.auth.model.LoginInput;
import br.forsign.allo.auth.service.AuthService;
import br.forsign.allo.provedor.model.ProvedorInput;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
@Tag(name = "Autenticacao", description = "Controle de autenticacao da aplicacão")
public class AuthController {

    @Resource
    private AuthService service;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginInput input) {
        return ResponseEntity.ok().body(this.service.login(input));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid AuthInput input) {

        this.service.register(input);

        return ResponseEntity.ok().build();
    }
}
