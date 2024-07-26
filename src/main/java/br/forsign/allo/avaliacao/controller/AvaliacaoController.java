package br.forsign.allo.avaliacao.controller;

import br.forsign.allo.avaliacao.model.AvaliacaoOutput;
import br.forsign.allo.avaliacao.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author kaiooliveira
 * created 23/06/2024
 */

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Resource
    private AvaliacaoService service;


    @GetMapping("/filter/provedor")
    @Operation(
            summary = "Consulta avaliações por profissão.",
            description = "Consulta avaliações por profissão."
    )
    @PreAuthorize("hasRole('ROLE_PROVEDOR') or hasRole('ROLE_CLIENTE')")
    public ResponseEntity<List<AvaliacaoOutput>> byProvedor(@RequestParam Long idProvedor) {
        return ResponseEntity.ok().body(this.service.byProvedor(idProvedor));
    }
}
