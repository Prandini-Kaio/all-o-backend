package br.forsign.allo.cliente.controller;

/*
 * @author prandini
 * created 5/24/24
 */

import br.forsign.allo.cliente.model.PerfilClienteOutput;
import br.forsign.allo.cliente.service.PerfilClienteService;
import br.forsign.allo.provedor.model.PerfilProvedorOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente/perfil")
@Tag(name = "Cliente")
public class PerfilClienteController {

    @Resource
    private PerfilClienteService service;

    @GetMapping
    @Operation(
            summary = "Consulta o perfil de um cliente.",
            description = "Consulta o perfil de um cliente ativo e previamente cadastrado.")
    public ResponseEntity<PerfilClienteOutput> getByClienteId(@RequestParam Long idCliente){
        return ResponseEntity.ok().body(this.service.getByClienteId(idCliente));
    }
}
