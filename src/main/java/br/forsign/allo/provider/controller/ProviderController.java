package br.forsign.allo.provider.controller;

import br.forsign.allo.provider.service.ProviderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
@Tag(name = "Provedor")
public class ProviderController {

    @Autowired
    private ProviderService service;
}
