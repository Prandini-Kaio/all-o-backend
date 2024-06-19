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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            summary = "Retorna um prestador por id.",
            description = "Retorna um prestador por id e ativo."
    )
    public ResponseEntity<ProvedorOutput> getById(
            @RequestParam Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/{idProfissao}")
    @Operation(
            summary = "Retorna todos os prestadores de uma profissão.",
            description = "Retorna todos os prestadores ativos de uma profissão."
    )
    public ResponseEntity<Page<ProvedorOutput>> getByProfissao(
            @PathVariable Long idProfissao,
            @PageableDefault(size = 15) Pageable pageable){

        return ResponseEntity.ok().body(service.findByProfissao(idProfissao, pageable));
    }

    @GetMapping("/filter")
    @Operation(
            summary = "Retorna todos os prestadores com base em um filtro.",
            description = "Retorna todos os prestadores ativos com base em um filtro."
    )
    public ResponseEntity<Page<ProvedorOutput>> getByFilter(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String profissao,
            @PageableDefault(size = 15) Pageable pageable){
        return ResponseEntity.ok().body(service.findByFilter(nome, profissao, pageable));
    }

    @GetMapping("/favoritos")
    @Operation(
            summary = "Retorna um prestador favorito.",
            description = "Retorna um prestador favorito."
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
    public ResponseEntity<Void> delById(@PathVariable Long id){
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }
}
