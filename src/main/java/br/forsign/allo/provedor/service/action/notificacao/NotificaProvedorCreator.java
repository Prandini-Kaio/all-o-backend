package br.forsign.allo.provedor.service.action.notificacao;

import br.forsign.allo.provedor.domain.NotificacaoProvedor;
import br.forsign.allo.provedor.model.NotificacaoProvedorInput;
import br.forsign.allo.provedor.repository.NotificacaoProvedorRepository;
import jakarta.annotation.Resource;
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

    public NotificacaoProvedor create(NotificacaoProvedorInput input){

        NotificacaoProvedor notificacao = new NotificacaoProvedor();

        notificacao.setTitulo(input.getTitulo());
        notificacao.setMensagem(input.getMensagem());
        notificacao.setVisualizada(input.isVisualizada());
        notificacao.setDataCriacao(LocalDate.now());

        return repository.save(notificacao);
    }
}
