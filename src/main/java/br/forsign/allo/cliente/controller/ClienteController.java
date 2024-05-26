package br.forsign.allo.cliente.controller;

import br.forsign.allo.cliente.model.ClienteInput;
import br.forsign.allo.cliente.model.ClienteOuput;
import br.forsign.allo.cliente.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cliente")
@Tag(name = "Cliente")
public class ClienteController {

    @Resource
    private ClienteService service;

    @GetMapping
    @Operation(
            summary = "Consulta todos os clientes.",
            description = "Consulta todos os cientes cadastrador e ativos.")
    private ResponseEntity<List<ClienteOuput>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    @Operation(
            summary = "Cria um novo cliente.",
            description = "Cria um novo cliente e cadastra na base.")
    public ResponseEntity<ClienteOuput> create(@RequestBody @Valid ClienteInput input){
        return ResponseEntity.ok().body(service.create(input));
    }

    @PutMapping
    @Operation(
            summary = "Atualiza um cliente",
            description = "Atualiza um cliente previamente cadastrado.")
    public ResponseEntity<ClienteOuput> update(@RequestBody @Valid ClienteInput inputDTO){
        return ResponseEntity.ok().body(service.update(inputDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Desativa um cliente",
            description = "Desativa um cliente previamente cadastrado.")
    public ResponseEntity delById(@PathVariable Long id){
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/favoritar")
    @Operation(
            summary = "Favorita um provedor",
            description = "Adiciona um provedor a lista de favoritos de um cliente.")
    public ResponseEntity<ClienteOuput> favoritar(
            @RequestParam Long idCliente,
            @RequestParam Long idProvedor
    ){
        return ResponseEntity.ok().body(service.favoritar(idCliente, idProvedor));
    }
}