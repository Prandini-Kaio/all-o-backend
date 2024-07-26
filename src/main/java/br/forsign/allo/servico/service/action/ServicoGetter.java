package br.forsign.allo.servico.service.action;

import br.forsign.allo.auth.service.AuthService;
import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.service.actions.ClienteGetter;
import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.service.action.ProvedorGetter;
import br.forsign.allo.servico.domain.Servico;
import br.forsign.allo.servico.repository.ServicoRepository;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.catalina.mapper.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author kaiooliveira
 * created 23/06/2024
 */

@Component
@CommonsLog
public class ServicoGetter {

    @Resource
    private ServicoRepository repository;

    @Autowired
    private ProvedorGetter provedorGetter;

    @Autowired
    private ClienteGetter clienteGetter;

    public Servico byId(Long id){

        log.info(String.format("Buscando serviço pelo id: %s", id));

        return repository.findById(id).orElseThrow(CommonExceptionSupplier.naoEncontrado("Servico", id));
    }

    public List<Servico> byNaoVistoPeloProvedor() {
        Provedor provedor = provedorGetter.byUsername(AuthService.getContextUser().getUsername());

        log.info(String.format("Buscando serviços não vistos pelo provedor %s", provedor.getRazaoSocial()));

        return repository.findByNaoVistoPeloProvedor(provedor.getId());
    }

    public List<Servico> byNaoVistoPeloCliente() {
        Cliente cliente = clienteGetter.byUsername(AuthService.getContextUser().getUsername());

        log.info(String.format("Buscando serviços não vistos pelo cliente %s", cliente.getNome()));

        return repository.findByNaoVistoPeloCliente(cliente.getId());
    }

    public List<Servico> byProvedor(Long idProvedor) {

        log.info(String.format("Buscando serviços pelo provedor %s", idProvedor));

        return this.repository.byProvedor(idProvedor);
    }

    public Servico byClienteAndId(String cliente, Long idServico) {
        log.info(String.format("Consultando serviço do cliente %s e ID serviço %s", cliente, idServico));

        return repository.findByClienteAndId(cliente, idServico).orElseThrow(CommonExceptionSupplier.naoEncontrado("Servico", cliente));
    }

    public Servico byProvedorAndID(String provedor, Long idServico) {
        log.info(String.format("Consultando serviço do provedor %s e ID serviço %s", provedor, idServico));

        return repository.findByProvedorAndId(provedor, idServico).orElseThrow(CommonExceptionSupplier.naoEncontrado("Servico", provedor));
    }
}
