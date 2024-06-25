package br.forsign.allo.servico.service.action;

import br.forsign.allo.avaliacao.converter.AvaliacaoMapper;
import br.forsign.allo.avaliacao.domain.Avaliacao;
import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.provedor.model.NotificacaoProvedorInput;
import br.forsign.allo.provedor.service.NotificacaoProvedorService;
import br.forsign.allo.provedor.service.PerfilProvedorService;
import br.forsign.allo.provedor.service.action.ProvedorUpdater;
import br.forsign.allo.provedor.service.action.perfil.PerfilProvedorUpdater;
import br.forsign.allo.servico.domain.Servico;
import br.forsign.allo.servico.model.ServicoInput;
import br.forsign.allo.servico.repository.ServicoRepository;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Resource
    private NotificacaoProvedorService notificacaoProvedorService;

    private final Logger logger = LoggerFactory.getLogger(ServicoUpdater.class);

    public Servico confirmarServico(Long idServico, Boolean confirmado) {

        logger.info("Confirmando ( {} ) serviço: {}", confirmado, idServico);

        Servico servico = getter.byId(idServico);

        servico.setServicoRealizado(confirmado);
        servico.setServicoVisto(true);

        return this.repository.save(servico);
    }

    public Servico avaliarServico(ServicoInput input) {

        logger.info("Avaliando serviço: {}", input.getId());

        Servico servico = getter.byId(input.getId());

        String mensagem = String.format("O cliente %s avaliou o serviço!", servico.getCliente().getNome());

        servico.setAvaliacao(avaliacaoMapper.fromInput(input.getAvaliacao()));

        perfilProvedorUpdater.updateStats(servico.getProvedor().getId(), input.getId());

        notificacaoProvedorService.createAvaliacao(new NotificacaoProvedorInput(mensagem));

        return this.repository.save(servico);
    }
}
