package br.forsign.allo.servico.service.action;

import br.forsign.allo.avaliacao.converter.AvaliacaoMapper;
import br.forsign.allo.avaliacao.domain.Avaliacao;
import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.provedor.service.PerfilProvedorService;
import br.forsign.allo.provedor.service.action.ProvedorUpdater;
import br.forsign.allo.provedor.service.action.perfil.PerfilProvedorUpdater;
import br.forsign.allo.servico.domain.Servico;
import br.forsign.allo.servico.model.ServicoInput;
import br.forsign.allo.servico.repository.ServicoRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author kaiooliveira
 * created 23/06/2024
 */

@Component
public class ServicoUpdater {

    @Resource
    private ServicoRepository repository;

    @Resource
    private ServicoGetter getter;

    @Resource
    private PerfilProvedorUpdater perfilProvedorUpdater;

    @Resource
    private AvaliacaoMapper avaliacaoMapper;

    public Servico confirmarServico(Long idServico, Boolean confirmado) {
        Servico servico = getter.byId(idServico);

        servico.setServicoRealizado(confirmado);
        servico.setServicoVisto(true);

        return this.repository.save(servico);
    }

    public Servico avaliarServico(ServicoInput input) {
        Servico servico = getter.byId(input.getId());

        servico.setAvaliacao(avaliacaoMapper.fromInput(input.getAvaliacao()));

        perfilProvedorUpdater.updateStats(servico.getProvedor().getId(), input.getId());

        return this.repository.save(servico);
    }
}
