package br.forsign.allo.profissao.controller;

/*
 * @author prandini
 * created 5/25/24
 */

import br.forsign.allo.profissao.model.ProfissaoOutput;
import br.forsign.allo.profissao.service.ProfissaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profissao")
@Tag(name = "Profissão")
public class ProfissaoController {

    @Autowired
    private ProfissaoService service;

    @GetMapping
    @Operation(
            summary = "Consulta todas as profissões.",
            description = "Consulta todas as profissões cadastradas no sistema.")
    public ResponseEntity<Page<ProfissaoOutput>> findAll(Pageable pageable){
        return ResponseEntity.ok().body(this.service.findAll(pageable));
    }
}
