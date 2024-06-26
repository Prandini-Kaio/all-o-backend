package br.forsign.allo.servico.service.action;

import br.forsign.allo.auth.service.AuthService;
import br.forsign.allo.avaliacao.converter.AvaliacaoMapper;
import br.forsign.allo.avaliacao.domain.Avaliacao;
import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.service.actions.ClienteGetter;
import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.NotificacaoProvedorInput;
import br.forsign.allo.provedor.service.NotificacaoProvedorService;
import br.forsign.allo.provedor.service.PerfilProvedorService;
import br.forsign.allo.provedor.service.action.ProvedorGetter;
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

    @Resource
    private ProvedorGetter provedorGetter;

    @Resource
    private ClienteGetter clienteGetter;

    @Resource
    private AuthService authService;

    private final Logger logger = LoggerFactory.getLogger(ServicoUpdater.class);

    public Servico confirmarServico(Long idServico, Boolean confirmado) {

        logger.info("Confirmando ( {} ) serviço: {}", confirmado, idServico);

        Provedor provedor = provedorGetter.byUsername(AuthService.getContextUser().getUsername());

        Servico servico = getter.byProvedorAndID(provedor.getUsuario().getUsername(), idServico);

        servico.setServicoRealizado(confirmado);
        servico.setServicoVisto(true);

        return this.repository.save(servico);
    }

    public Servico avaliarServico(ServicoInput input) {

        Cliente cliente = clienteGetter.byUsername(AuthService.getContextUser().getUsername());
        Servico servico = getter.byClienteAndId(cliente.getUsuario().getUsername(), input.getId());
        String mensagem = String.format("O cliente %s avaliou o serviço!", cliente.getNome());

        logger.info("Avaliando serviço: {}", input.getId());

        NotificacaoProvedorInput notificacaoProvedorInput = new NotificacaoProvedorInput();

        notificacaoProvedorInput.setIdProvedor(servico.getProvedor().getId());
        notificacaoProvedorInput.setNomeCliente(cliente.getNome());
        notificacaoProvedorInput.setMensagem(mensagem);

        servico.setAvaliacao(avaliacaoMapper.fromInput(input.getAvaliacao()));

        notificacaoProvedorService.createAvaliacao(notificacaoProvedorInput);

        servico = this.repository.save(servico);

        perfilProvedorUpdater.updateStats(servico.getProvedor().getId(), input.getId());
        return servico;
    }
}
