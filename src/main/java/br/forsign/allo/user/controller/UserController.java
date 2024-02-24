package br.forsign.allo.user.controller;

import br.forsign.allo.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping("/create")
    @Operation(summary = "Criar usuario")
    private void createUser(String user){
        service.createUser(user);
    }
}
