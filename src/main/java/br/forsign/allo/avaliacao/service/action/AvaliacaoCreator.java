package br.forsign.allo.avaliacao.service.action;

/*
 * @author prandini
 * created 5/26/24
 */

import br.forsign.allo.avaliacao.domain.Avaliacao;
import br.forsign.allo.avaliacao.model.AvaliacaoInput;
import br.forsign.allo.avaliacao.repository.AvaliacaoRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoCreator {

    @Resource
    private AvaliacaoRepository repository;

    public Avaliacao avaliar(AvaliacaoInput input) {
        Avaliacao avaliacao = new Avaliacao();

        avaliacao.setDescricao(input.getDescricao());
        avaliacao.setNota(input.getNota());

        return repository.save(avaliacao);
    }
}
