package br.forsign.allo.servico.service.action;

import br.forsign.allo.avaliacao.converter.AvaliacaoMapper;
import br.forsign.allo.servico.domain.Servico;
import br.forsign.allo.servico.model.ServicoInput;
import br.forsign.allo.servico.service.repository.ServicoRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * @author kaiooliveira
 * created 23/06/2024
 */

@Component
public class ServiceUpdater {

    @Resource
    private ServicoRepository repository;

    @Resource
    private AvaliacaoMapper avaliacaoMapper;

    public Servico update(ServicoInput input){
        Servico servico = repository.getReferenceById(input.getId());

        servico.setAvaliacao(avaliacaoMapper.fromOutput(input.getAvaliacao()));
        servico.setServicoRealizado(input.isServicoRealizado());

        return this.repository.save(servico);
    }
}
