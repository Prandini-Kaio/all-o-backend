package br.forsign.allo.servico;

import br.forsign.allo.servico.model.ServicoInput;
import br.forsign.allo.servico.model.ServicoOutput;
import br.forsign.allo.servico.service.ServicoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/abertura")
    @Operation(
            summary = "Abertura de Serviço",
            description = "Abertura de Serviço entre Cliente e Provedor"
    )
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    public ResponseEntity<ServicoOutput> abrirServico(@RequestParam("idProvedor") Long idProvedor){
        return ResponseEntity.ok().body(service.requisitarServico(idProvedor));
    }

    @PutMapping("/confirmacao")
    @Operation(
            summary = "Confirmação de Serviço",
            description = "Confirmação de Serviço entre Cliente e Provedor"
    )
    @PreAuthorize("hasRole('ROLE_PROVEDOR')")
    public ResponseEntity<ServicoOutput> confirmarServico(@RequestParam("idServico") Long idServico){
        return ResponseEntity.ok().body(service.confirmarServico(idServico));
    }

    @PutMapping("/avaliar")
    @Operation(
            summary = "Avaliação de Serviço",
            description = "Avaliação de Serviço entre Cliente e Provedor"
    )
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    public ResponseEntity<ServicoOutput> avaliarServico(@RequestBody ServicoInput input){
        return ResponseEntity.ok().body(service.avaliarServico(input));
    }
}
