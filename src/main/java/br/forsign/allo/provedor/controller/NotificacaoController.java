package br.forsign.allo.provedor.controller;

import br.forsign.allo.provedor.model.NotificacaoOutput;
import br.forsign.allo.provedor.model.NotificacaoProvedorInput;
import br.forsign.allo.provedor.service.NotificacaoProvedorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author kaiooliveira
 * created 18/06/2024
 */

@RestController("/notificacao")
public class NotificacaoController {

    @Resource
    private NotificacaoProvedorService service;

    @GetMapping
    @Operation(
            summary = "Lista notificações",
            description = "Lista todas as notificações do provedor"
    )
    @PreAuthorize("hasRole('ROLE_PROVEDOR')")
    public ResponseEntity<List<NotificacaoOutput>> listNotificacoes() {
        return ResponseEntity.ok().body(this.service.byProvedor());
    }

//    @PostMapping
//    @Operation(
//            summary = "Registra uma notificação",
//            description = "Registra uma nova notificação para o provedor"
//    )
//    public ResponseEntity<NotificacaoOutput> createNotificacao(@RequestBody @Valid NotificacaoProvedorInput input) {
//        return ResponseEntity.ok().body(this.service.create(input));
//    }

    @PutMapping("/visualizar/{id}")
    @Operation(
            summary = "Visualizar notificação",
            description = "Marca a notificação como visualizada"
    )
    public ResponseEntity<NotificacaoOutput> visualizarNotificacao(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.service.visualizarNotificacao(id));
    }
}
