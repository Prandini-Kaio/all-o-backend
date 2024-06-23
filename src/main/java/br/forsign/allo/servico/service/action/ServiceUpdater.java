package br.forsign.allo.servico.service.action;

import br.forsign.allo.avaliacao.converter.AvaliacaoMapper;
import br.forsign.allo.common.utils.CommonExceptionSupplier;
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
    private ServicoGetter getter;

    @Resource
    private AvaliacaoMapper avaliacaoMapper;

    public Servico confirmarServico(Long idServico) {
        Servico servico = repository.findById(idServico).orElseThrow(CommonExceptionSupplier.naoEncontrado("Servico", idServico));

        servico.setServicoRealizado(true);
        servico.setServicoVisto(true);

        return this.repository.save(servico);
    }

    public Servico avaliarServico(ServicoInput input) {
        Servico servico = getter.byId(input.getId());

        servico.setAvaliacao(avaliacaoMapper.fromInput(input.getAvaliacao()));

        return this.repository.save(servico);
    }
}
