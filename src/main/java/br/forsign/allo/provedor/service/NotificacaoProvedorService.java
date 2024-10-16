package br.forsign.allo.provedor.service;

import br.forsign.allo.provedor.converter.NotificacaoProvedorMapper;
import br.forsign.allo.provedor.model.NotificacaoOutput;
import br.forsign.allo.provedor.model.NotificacaoProvedorInput;
import br.forsign.allo.provedor.model.NotificacaoProvedorOutput;
import br.forsign.allo.provedor.service.action.notificacao.NotificaProvedorCreator;
import br.forsign.allo.provedor.service.action.notificacao.NotificacaoProvedorGetter;
import br.forsign.allo.provedor.service.action.notificacao.NotificacaoProvedorUpdater;
import br.forsign.allo.usuario.domain.Usuario;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kaiooliveira
 * created 18/06/2024
 */

@Service
@CommonsLog
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
        log.info("Iniciando criação de notificaçao");

        return mapper.toOutput(this.creator.create(input));
    }

    public NotificacaoOutput createAvaliacao(NotificacaoProvedorInput input) {

        log.info("Iniciando criação de notificaçao de avaliação");

        return mapper.toOutput(this.creator.createAvaliacao(input));
    }

    public NotificacaoOutput visualizarNotificacao(Long id) {
        log.info(String.format("Iniciando visualização de notificação %s", id));

        return mapper.toOutput(this.updater.visualizarNotificacao(id));
    }

    public List<NotificacaoProvedorOutput> byProvedor() {
        log.info("Iniciando busca de notificações do provedor");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) authentication.getPrincipal();

        return this.getter.byUsername(usuario.getUsername()).stream().map(mapper::toProvedorOutput).toList();
    }
}
