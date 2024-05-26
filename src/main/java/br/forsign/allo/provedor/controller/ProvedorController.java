package br.forsign.allo.provedor.controller;


import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.provedor.model.ProvedorOutput;
import br.forsign.allo.provedor.service.ProvedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provedor")
@Tag(
        name = "Provedor",
        description = "Endpoints relacionados ao prestador de serviços.")
public class ProvedorController {

    @Resource
    private ProvedorService service;



    @GetMapping
    @Operation(
            summary = "Procura um prestador com base em um filtro.",
            description = "Procura por um prestador com base em informações úteis."
    )
    public ResponseEntity<Page<ProvedorOutput>> getByFilter(
            @RequestParam Long idCliente,
            @PageableDefault(size = 15) Pageable pageable){
        return ResponseEntity.ok().body(service.findAllComFavoritos(idCliente, pageable));
    }

    @PostMapping
    @Operation(
            summary = "Cria um prestador.",
            description = "Cria um prestador com todas as informações necessárias."
    )
    public ResponseEntity<ProvedorOutput> create(@RequestBody @Valid ProvedorInput input){
        return ResponseEntity.ok().body(service.create(input));
    }

    @PutMapping
    @Operation(
            summary = "Atualiza os dados de um prestador.",
            description = "Atualiza um prestador previamente cadastrado no sistema."
    )
    public ResponseEntity updateById(@RequestBody @Valid ProvedorInput input){
        return ResponseEntity.ok().body(service.update(input));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deleta um prestador.",
            description = "Deleta um prestador previamente cadastrado, com base no identificador de cadastro."
    )
    public ResponseEntity delById(@PathVariable Long id){
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }
}
