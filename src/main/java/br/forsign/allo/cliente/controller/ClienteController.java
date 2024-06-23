package br.forsign.allo.cliente.controller;

import br.forsign.allo.cliente.model.ClienteCadastroInput;
import br.forsign.allo.cliente.model.ClienteInput;
import br.forsign.allo.cliente.model.ClienteOuput;
import br.forsign.allo.cliente.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.id.GUIDGenerator;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

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
    private ResponseEntity<List<ClienteOuput>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Consulta o cliente por id.",
            description = "Consulta o ciente cadastrado e ativo.")
    private ResponseEntity<ClienteOuput> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping("/register")
    @Operation(
            summary = "Cria um novo cliente.",
            description = "Cria um novo cliente e cadastra na base.")
    public ResponseEntity<ClienteOuput> create(@RequestBody @Valid ClienteCadastroInput input) {
        return ResponseEntity.ok().body(service.create(input));
    }

    @PutMapping
    @Operation(
            summary = "Atualiza um cliente",
            description = "Atualiza um cliente previamente cadastrado.")
    public ResponseEntity<ClienteOuput> update(@RequestBody @Valid ClienteInput inputDTO) {
        return ResponseEntity.ok().body(service.update(inputDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Desativa um cliente",
            description = "Desativa um cliente previamente cadastrado.")
    public ResponseEntity<Void> delById(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/favoritar")
    @Operation(
            summary = "Favorita um provedor",
            description = "Adiciona um provedor a lista de favoritos de um cliente.")
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    public ResponseEntity<ClienteOuput> favoritar(
            @RequestParam Long idProvedor
    ) {
        return ResponseEntity.ok().body(service.favoritar(idProvedor));
    }

    @PostMapping("/upload")
    @Operation(summary = "Sobe uma imagem",
               description = "Sobe uma imagem para um cliente.")
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    public String handleFileUpload(@RequestParam("image") MultipartFile file) {
        return service.postImageCliente(file);
    }

    @GetMapping("/buscarImagem")
    @Operation(summary = "Busca uma imagem",
               description = "Busca uma imagem pelo Nome.")
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    public ResponseEntity<org.springframework.core.io.Resource> buscarImagemPorNome(@RequestParam String fileName) {
        return service.getImage(fileName);
    }
}
