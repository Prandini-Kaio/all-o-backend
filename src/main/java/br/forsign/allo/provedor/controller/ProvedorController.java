package br.forsign.allo.provedor.controller;


import br.forsign.allo.provedor.domain.TipoUpload;
import br.forsign.allo.provedor.model.*;
import br.forsign.allo.provedor.service.ProvedorService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
            summary = "Retorna um prestador por um filtro.",
            description = "Retorna um prestador por um filtro, tendo sua ordenação com base nos booleanos requeridos. " +
                    "A principio, se ambos forem verdadeiros, a ordenação sera feita com base nos melhores avaliados e com criterio de desempate nos mais relevantes."
    )

    public ResponseEntity<List<ProvedorOutput>> getByFilter(
            @ModelAttribute ProvedorFilter filter
    ){
        return ResponseEntity.ok().body(service.findByFilter(filter));
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
    public ResponseEntity<Page<ProvedorOutput>> getFavoritosByFilter(
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
    @Operation(summary = "Sobe uma imagem",
               description = "Sobe uma imagem para um provedor")
    public String handleFileUpload(@RequestParam("image") MultipartFile file) {
        return this.service.postImage(file, TipoUpload.PERFIL);
    }

    @PostMapping("/upload/servico")
    @Operation(summary = "Sobe uma imagem",
            description = "Sobe uma imagem para um provedor")
    public String handleFileUploadServico(@RequestParam("image") MultipartFile file) {
        return this.service.postImage(file, TipoUpload.SERVICO);
    }

    @GetMapping("/buscarImagem")
    @Operation(summary = "Busca uma imagem",
               description = "Busca uma imagem pelo Nome.")
    public ResponseEntity<org.springframework.core.io.Resource> buscarImagemPorNome(@RequestParam String fileName) {
         return service.getImage(fileName, TipoUpload.PERFIL);
    }

    @GetMapping("/buscarImagem/servico")
    @Operation(summary = "Busca uma imagem",
            description = "Busca uma imagem pelo Nome.")
    public ResponseEntity<org.springframework.core.io.Resource> buscarImagemServicoPorNome(@RequestParam String fileName) {
        return service.getImage(fileName, TipoUpload.SERVICO);
    }

    @GetMapping("/melhoresAvaliados")
    @Operation(summary = "Retorna os provedores mais bem-avaliados",
               description = "Retorna os provedores mais bem avaliados")
    public ResponseEntity<List<ProvedorDestaquesOutput>> getByHighAvaliacao(){
        return ResponseEntity.ok().body(service.getMelhoresAvaliados());
    }

    @GetMapping("/melhoresAvaliados/profissao")
    @Operation(summary = "Retorna os provedores mais bem-avaliados",
            description = "Retorna os provedores mais bem avaliados")
    public ResponseEntity<List<ProvedorDestaquesOutput>> getMelhoresAvaliados(@RequestParam Long idProfissao){
        return ResponseEntity.ok().body(service.getByHighAvaliacao(idProfissao));
    }

    @GetMapping("/maisRelevantes")
    @Operation(summary = "Retorna os mais relevantes",
            description = "Retorna os provedores com mais serviço no mes")
    public ResponseEntity<List<ProvedorOutput>> getByMostRelevant(@RequestParam Long idProfissao){
        return ResponseEntity.ok().body(service.getByMostRelevant(idProfissao));
    }

}
