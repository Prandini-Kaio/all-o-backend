package br.forsign.allo.servico.service.action;

import br.forsign.allo.auth.service.AuthService;
import br.forsign.allo.avaliacao.converter.AvaliacaoMapper;
import br.forsign.allo.cliente.converter.ClienteMapper;
import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.service.actions.ClienteGetter;
import br.forsign.allo.provedor.converter.ProvedorMapper;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.service.action.ProvedorGetter;
import br.forsign.allo.servico.domain.Servico;
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
public class ServicoCreator {


    @Resource
    private ClienteGetter clienteGetter;

    @Resource
    private ProvedorGetter provedorGetter;

    @Resource
    private ServicoRepository repository;

    private final Logger logger = LoggerFactory.getLogger(ServicoCreator.class);

    public Servico abrirServico(Long idProvedor) {

        logger.info("Criando serviço para o provedor: {}", idProvedor);

        Cliente cliente = clienteGetter.byUsername(AuthService.getContextUser().getUsername());
        Provedor provedor = provedorGetter.byId(idProvedor);

        Servico servico = new Servico();

        servico.setCliente(cliente);
        servico.setProvedor(provedor);
        servico.setServicoRealizado(false);
        servico.setServicoVisto(false);

        return repository.save(servico);
    }
}
