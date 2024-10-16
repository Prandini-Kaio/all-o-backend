package br.forsign.allo.provedor.controller;


import br.forsign.allo.provedor.model.PerfilProvedorInput;
import br.forsign.allo.provedor.model.PerfilProvedorOutput;
import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.provedor.service.PerfilProvedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provedor/perfil")
@Tag(name = "Provedor")
public class PerfilProvedorController {

    @Resource
    private PerfilProvedorService service;

    @GetMapping
    @Operation(
            summary = "Consulta o perfil de um provedor.",
            description = "Consulta o perfil de um provedor previamente cadastrado no sitema e ativo.")
    public ResponseEntity<PerfilProvedorOutput> getByProvedorId(@RequestParam Long idProvedor) {
        return ResponseEntity.ok().body(this.service.getByProvedorId(idProvedor));
    }

    @PutMapping
    @Operation(
            summary = "Atualiza o perfil de um provedor.",
            description = "Atualiza o perfil de um provedor ativo.")
    @PreAuthorize("hasRole('PROVEDOR') or hasRole('ADMIN')")
    public ResponseEntity<PerfilProvedorOutput> update(@RequestBody @Valid ProvedorInput provedorInput){
        return ResponseEntity.ok().body(this.service.update(provedorInput));
    }

    @PutMapping("/destacar")
    @Operation(
            summary = "Altera a avaliacao em destaque do perfil provedor.",
            description = "Altera a avaliacao em destaque do perfil provedor.")
    @PreAuthorize("hasRole('PROVEDOR') or hasRole('ADMIN')")
    public ResponseEntity<PerfilProvedorOutput> destacarAvaliacao(@RequestBody Long idAvaliacao){
        return ResponseEntity.ok().body(this.service.destacarAvaliacao(idAvaliacao));
    }

}
