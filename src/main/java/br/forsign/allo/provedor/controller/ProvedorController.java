package br.forsign.allo.provedor.controller;


import br.forsign.allo.provedor.domain.TipoUpload;
import br.forsign.allo.provedor.model.*;
import br.forsign.allo.provedor.service.ProvedorService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.core.io.InputStreamResource;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @GetMapping("/filter/profissao")
    @Operation(
            summary = "Retorna todos os prestadores de uma profissão.",
            description = "Retorna todos os prestadores ativos de uma profissão."
    )
    public ResponseEntity<List<ProvedorListOutput>> getByProfissao(@RequestParam Long idProfissao){

        return ResponseEntity.ok().body(service.findByProfissao(idProfissao));
    }


    @GetMapping("/favoritos")
    @Operation(
            summary = "Retorna um prestador favorito.",
            description = "Retorna um prestador favorito."
    )
    public ResponseEntity<Page<ProvedorOutput>> getByFilter(
            @PageableDefault(size = 15) Pageable pageable){
        return ResponseEntity.ok().body(service.findAllComFavoritos(pageable));
    }

    @PostMapping("/register")
    @Operation(
            summary = "Cria um prestador.",
            description = "Cria um prestador com todas as informações necessárias."
    )
    public ResponseEntity<ProvedorOutput> create(@RequestBody @Valid ProvedorCadastroInput input){
        return ResponseEntity.ok().body(service.create(input));
    }

    @PutMapping
    @Operation(
            summary = "Atualiza os dados de um prestador.",
            description = "Atualiza um prestador previamente cadastrado no sistema."
    )
    public ResponseEntity<ProvedorOutput> updateById(@RequestBody @Valid ProvedorInput input){
        return ResponseEntity.ok().body(service.update(input));
    }

    @Hidden
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deleta um prestador.",
            description = "Deleta um prestador previamente cadastrado, com base no identificador de cadastro."
    )
    public ResponseEntity<Void> delById(@PathVariable Long id){
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/upload")
    @Operation(summary = "Realiza o upload de uma imagem",
               description = "Faz o upload de uma imagem para o perfil de um provedor")
    public ResponseEntity<String> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("tipoUpload") TipoUpload tipoUpload,
            @RequestParam("id") Long id
    ) {
        return ResponseEntity.ok().body(this.service.uploadImage(file, tipoUpload, id));
    }

    @GetMapping("/buscarImagem")
    @Operation(summary = "Busca uma imagem",
            description = "Busca uma imagem pelo id do provedor.")
    public ResponseEntity<InputStreamResource> buscarImagemPorNome(
            @RequestParam Long idProvedor,
            @RequestParam TipoUpload tipoUpload
    ) {
        return this.service.findImage(TipoUpload.PERFIL, idProvedor);
    }

    @GetMapping("/melhoresAvaliados")
    @Operation(summary = "Retorna os provedores mais bem-avaliados",
               description = "Retorna os provedores mais bem avaliados")
    public ResponseEntity<List<ProvedorDestaquesOutput>> getByHighAvaliacao(){
        return ResponseEntity.ok().body(service.getByHighAvaliacao());
    }

    @GetMapping("/filter")
    @Operation(summary = "Retorna os provedores com base em um filtro",
               description = "Retorna os provedores com base em um filtro")
    public ResponseEntity<List<ProvedorOutput>> getByFilter(ProvedorFilter filter){
        return ResponseEntity.ok().body(service.findByFilter(filter));
    }

    @GetMapping("/maisRelevantes")
    @Operation(summary = "Retorna os mais relevantes",
            description = "Retorna os provedores com mais serviço no mes")
    public ResponseEntity<List<ProvedorOutput>> getByMostRelevant(){
        return ResponseEntity.ok().body(service.getByMostRelevant());
    }

}
