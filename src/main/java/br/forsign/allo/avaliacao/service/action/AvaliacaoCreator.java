package br.forsign.allo.avaliacao.service.action;

/*
 * @author prandini
 * created 5/26/24
 */

import br.forsign.allo.avaliacao.domain.Avaliacao;
import br.forsign.allo.avaliacao.model.AvaliacaoInput;
import br.forsign.allo.avaliacao.repository.AvaliacaoRepository;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

@Component
@CommonsLog
public class AvaliacaoCreator {

    @Resource
    private AvaliacaoRepository repository;

    public Avaliacao avaliar(AvaliacaoInput input) {
        log.info("Cadastrando avaliação para o provedor no sistema.");

        Avaliacao avaliacao = new Avaliacao();

        avaliacao.setDescricao(input.getDescricao());
        avaliacao.setNota(input.getNota());

        return repository.save(avaliacao);
    }
}
