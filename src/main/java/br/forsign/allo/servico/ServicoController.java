package br.forsign.allo.servico;

import br.forsign.allo.servico.model.ServicoOutput;
import br.forsign.allo.servico.service.ServicoService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kaiooliveira
 * created 23/06/2024
 */

@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Resource
    private ServicoService service;

    // END POINTSSEPARADOS
    // ENDPOINTS PRA CADA ROLE

    // ABERTURA DE SERVICO (CLIENTE)
    @PostMapping("/abertura")
    public ResponseEntity<ServicoOutput> abrirServico(@RequestParam("idProvedor") Long idProvedor){
        return ResponseEntity.ok().body(service.requisitarServico(idProvedor));
    }

    // CONFIRMACAO DE SERVICO (PROVEDOR)
    // AVALIACAO DE SERVICO (CLIENTE)
    // VISUALIZACAO DE SERVICO (PROVEDOR)


}
