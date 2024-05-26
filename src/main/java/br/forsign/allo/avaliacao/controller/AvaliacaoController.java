package br.forsign.allo.avaliacao.controller;

/*
 * @author prandini
 * created 5/26/24
 */

import br.forsign.allo.avaliacao.model.AvaliacaoInput;
import br.forsign.allo.avaliacao.model.AvaliacaoOutput;
import br.forsign.allo.avaliacao.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Resource
    private AvaliacaoService service;


    @GetMapping("/provedor")
    @Operation(
            summary = "Consulta todas as avaliações de um provedor.",
            description = "Consulta todas as avaliações cadastradas em nome de um provedor."
    )
    public ResponseEntity<Page<AvaliacaoOutput>> consultarProvedor(
            @RequestParam Long id,
            @PageableDefault(size = 15) Pageable pageable
    ){
        return ResponseEntity.ok().body(this.service.byProvedor(id, pageable));
    }

    @PostMapping
    @Operation(
            summary = "Cadastra uma avaliação.",
            description = "Cadastra uma avaliação a um provedor cadastrado."
    )
    public ResponseEntity<AvaliacaoOutput> avaliar(
            @RequestBody @Valid AvaliacaoInput input
    ){
        return ResponseEntity.ok().body(this.service.avaliar(input));
    }
}
