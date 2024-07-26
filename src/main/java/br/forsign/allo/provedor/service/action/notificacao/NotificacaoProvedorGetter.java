package br.forsign.allo.provedor.service.action.notificacao;

import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.provedor.domain.NotificacaoProvedor;
import br.forsign.allo.provedor.repository.NotificacaoProvedorRepository;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author kaiooliveira
 * created 18/06/2024
 */

@Component
@CommonsLog
public class NotificacaoProvedorGetter {

    @Resource
    private NotificacaoProvedorRepository repository;

    public NotificacaoProvedor getById(Long id){
        log.info(String.format("Buscando notificação %s", id));

        return repository.findById(id).orElseThrow(CommonExceptionSupplier.naoEncontrado("Notificação", id));
    }

    public List<NotificacaoProvedor> byUsername(String login) {
        log.info(String.format("Buscando notificações do provedor %s", login));

        return this.repository.findByUsername(login);
    }
}
