package br.forsign.allo.provedor.service.action.notificacao;

import br.forsign.allo.provedor.domain.NotificacaoProvedor;
import br.forsign.allo.provedor.repository.NotificacaoProvedorRepository;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author kaiooliveira
 * created 18/06/2024
 */

@Component
public class NotificacaoProvedorUpdater {

    @Resource
    private NotificacaoProvedorGetter getter;

    @Resource
    private NotificacaoProvedorRepository repository;

    private final Logger logger = LoggerFactory.getLogger(NotificacaoProvedorUpdater.class);

    public NotificacaoProvedor visualizarNotificacao(Long id) {

        logger.info("Visualizando notificação {}", id);

        NotificacaoProvedor notificacao = getter.getById(id);

        notificacao.setVisualizada(true);

        return repository.save(notificacao);
    }
}
