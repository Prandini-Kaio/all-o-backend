package br.forsign.allo.profissao.controller;

/*
 * @author prandini
 * created 5/25/24
 */

import br.forsign.allo.profissao.model.ProfissaoInput;
import br.forsign.allo.profissao.model.ProfissaoOutput;
import br.forsign.allo.profissao.service.ProfissaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profissao")
@Tag(name = "Profissão")
public class ProfissaoController {

    @Resource
    private ProfissaoService service;

    @GetMapping
    @Operation(
            summary = "Consulta todas as profissões.",
            description = "Consulta todas as profissões cadastradas no sistema.")
    public ResponseEntity<Page<ProfissaoOutput>> findAll(Pageable pageable){
        return ResponseEntity.ok().body(this.service.findAll(pageable));
    }

    @PostMapping
    @Operation(
            summary = "Cadastra uma nova profissão.",
            description = "Cadastra uma nova profissão no sistema.")
    public ResponseEntity<ProfissaoOutput> create(@RequestBody ProfissaoInput input){
        return ResponseEntity.ok().body(this.service.create(input));
    }

    @PostMapping("/sugerir")
    @Operation(
            summary = "Sugere uma nova profissão.",
            description = "Cadastra uma sugestão de uma nova profissão.")
    public ResponseEntity<ProfissaoOutput> createSugestion(@RequestParam String sugestao){
        return ResponseEntity.ok().body(this.service.createSugestion(sugestao));
    }

}
