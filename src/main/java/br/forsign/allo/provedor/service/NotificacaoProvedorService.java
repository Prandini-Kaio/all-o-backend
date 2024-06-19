package br.forsign.allo.provedor.service;

import br.forsign.allo.provedor.converter.NotificacaoProvedorMapper;
import br.forsign.allo.provedor.model.NotificacaoOutput;
import br.forsign.allo.provedor.model.NotificacaoProvedorInput;
import br.forsign.allo.provedor.service.action.notificacao.NotificaProvedorCreator;
import br.forsign.allo.provedor.service.action.notificacao.NotificacaoProvedorGetter;
import br.forsign.allo.provedor.service.action.notificacao.NotificacaoProvedorUpdater;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author kaiooliveira
 * created 18/06/2024
 */

@Service
public class NotificacaoProvedorService {

    @Resource
    private NotificacaoProvedorGetter getter;

    @Resource
    private NotificaProvedorCreator creator;

    @Resource
    private NotificacaoProvedorUpdater updater;

    @Resource
    private NotificacaoProvedorMapper mapper;

    public NotificacaoOutput create(NotificacaoProvedorInput input) {
        return mapper.toOutput(this.creator.create(input));
    }

    public NotificacaoOutput visualizarNotificacao(Long id) {
        return mapper.toOutput(this.updater.visualizarNotificacao(id));
    }
}
