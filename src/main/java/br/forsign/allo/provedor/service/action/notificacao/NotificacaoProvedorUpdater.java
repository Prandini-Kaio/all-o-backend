package br.forsign.allo.provedor.service.action.notificacao;

import br.forsign.allo.provedor.domain.NotificacaoProvedor;
import br.forsign.allo.provedor.repository.NotificacaoProvedorRepository;
import jakarta.annotation.Resource;
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

    public NotificacaoProvedor visualizarNotificacao(Long id) {

        NotificacaoProvedor notificacao = getter.getById(id);

        notificacao.setVisualizada(true);

        return repository.save(notificacao);
    }
}
