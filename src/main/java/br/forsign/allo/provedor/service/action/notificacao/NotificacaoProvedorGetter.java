package br.forsign.allo.provedor.service.action.notificacao;

import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.provedor.domain.NotificacaoProvedor;
import br.forsign.allo.provedor.repository.NotificacaoProvedorRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author kaiooliveira
 * created 18/06/2024
 */

@Component
public class NotificacaoProvedorGetter {

    @Resource
    private NotificacaoProvedorRepository repository;

    public NotificacaoProvedor getById(Long id){
        return repository.findById(id).orElseThrow(CommonExceptionSupplier.naoEncontrado("Notificação", id));
    }
}
