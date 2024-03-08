package br.forsign.allo.provider.controller;


import br.forsign.allo.provider.model.ProviderInputDTO;
import br.forsign.allo.provider.model.ProviderOutputDTO;
import br.forsign.allo.provider.service.ProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
@Tag(name = "Provedor")
public class ProviderController {

    @Autowired
    private ProviderService service;

    @PostMapping
    @Operation(summary = "Cria um prestador.")
    public ResponseEntity<ProviderOutputDTO> create(@RequestBody @Valid ProviderInputDTO inputDTO){
        return ResponseEntity.ok().body(service.create(inputDTO));
    }

}
