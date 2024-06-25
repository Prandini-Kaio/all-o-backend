package br.forsign.allo.provedor.service.action.notificacao;

import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.provedor.domain.NotificacaoProvedor;
import br.forsign.allo.provedor.repository.NotificacaoProvedorRepository;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author kaiooliveira
 * created 18/06/2024
 */

@Component
public class NotificacaoProvedorGetter {

    @Resource
    private NotificacaoProvedorRepository repository;

    private final Logger logger = LoggerFactory.getLogger(NotificacaoProvedorGetter.class);

    public NotificacaoProvedor getById(Long id){
        logger.info("Buscando notificação {}", id);

        return repository.findById(id).orElseThrow(CommonExceptionSupplier.naoEncontrado("Notificação", id));
    }

    public List<NotificacaoProvedor> byUsername(String login) {
        logger.info("Buscando notificações do provedor {}", login);

        return this.repository.findByUsername(login);
    }
}
