package br.forsign.allo.provedor.service.action.notificacao;

import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.service.actions.ClienteGetter;
import br.forsign.allo.provedor.domain.NotificacaoProvedor;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.NotificacaoProvedorInput;
import br.forsign.allo.provedor.repository.NotificacaoProvedorRepository;
import br.forsign.allo.provedor.service.NotificacaoProvedorService;
import br.forsign.allo.provedor.service.action.ProvedorGetter;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author kaiooliveira
 * created 18/06/2024
 */

@Component
public class NotificaProvedorCreator {

    @Resource
    private NotificacaoProvedorRepository repository;

    @Resource
    private ProvedorGetter provedorGetter;

    private final Logger logger = LoggerFactory.getLogger(NotificacaoProvedorService.class);

    public NotificacaoProvedor create(NotificacaoProvedorInput input){
        logger.info("Criando notificação de avaliação {}", input.getId());

        NotificacaoProvedor notificacao = new NotificacaoProvedor();

        notificacao.setMensagem(input.getMensagem());
        notificacao.setVisualizada(input.isVisualizada());
        notificacao.setDataCriacao(LocalDate.now());

        return repository.save(notificacao);
    }

    public NotificacaoProvedor createAvaliacao(NotificacaoProvedorInput input) {
        logger.info("Criando notificação de avaliação de serviço");

        NotificacaoProvedor notificacao = new NotificacaoProvedor();
        Provedor provedor = provedorGetter.byId(input.getIdProvedor());

        notificacao.setTitulo("Seu serviço foi avaliado!");
        notificacao.setNomeCliente(input.getNomeCliente());
        notificacao.setMensagem(input.getMensagem());
        notificacao.setVisualizada(false);
        notificacao.setDataCriacao(LocalDate.now());
        notificacao.setProvedor(provedor);

        return repository.save(notificacao);
    }
}
