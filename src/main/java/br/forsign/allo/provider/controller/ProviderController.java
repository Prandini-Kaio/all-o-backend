package br.forsign.allo.provider.controller;


import br.forsign.allo.provider.model.ProviderFilterDTO;
import br.forsign.allo.provider.model.ProviderInputDTO;
import br.forsign.allo.provider.model.ProviderOutputDTO;
import br.forsign.allo.provider.service.ProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provider")
@Tag(name = "Provedor")
public class ProviderController {

    @Autowired
    private ProviderService service;

    @GetMapping
    @Operation(summary = "Retorna um usuario por um filtro")
    public ResponseEntity<Page<ProviderOutputDTO>> getByFilter(@RequestBody @Valid ProviderFilterDTO filter, Pageable pageable){
        return ResponseEntity.ok().body(service.getByFilter(filter, pageable));
    }

    @PostMapping
    @Operation(summary = "Cria um prestador.")
    public ResponseEntity<ProviderOutputDTO> create(@RequestBody @Valid ProviderInputDTO inputDTO){
        return ResponseEntity.ok().body(service.create(inputDTO));
    }
}
