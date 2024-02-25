package br.forsign.allo.user.controller;

import br.forsign.allo.user.model.UserInputDTO;
import br.forsign.allo.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Tag(name = "Usuario")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    @Operation(summary = "Retorna todos os usuarios salvos.")
    private ResponseEntity findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    @Operation(summary = "Cria um novo usuario.")
    private ResponseEntity createUser(@RequestBody @Validated UserInputDTO inputDTO){
        return ResponseEntity.ok().body(service.create(inputDTO));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Devolve um Usuario pelo ID")
    private ResponseEntity getById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.getById(id));
    }

}
