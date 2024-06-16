package br.forsign.allo.provedor.controller;


import br.forsign.allo.provedor.model.PerfilProvedorInput;
import br.forsign.allo.provedor.model.PerfilProvedorOutput;
import br.forsign.allo.provedor.service.PerfilProvedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PerfilProvedorOutput> update(@RequestBody @Valid PerfilProvedorInput perfilProvedorInput){
        return ResponseEntity.ok().body(this.service.update(perfilProvedorInput));
    }

}
