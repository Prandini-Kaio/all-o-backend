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
import org.apache.catalina.mapper.Mapper;
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
public class ServicoGetter {

    @Resource
    private ServicoRepository repository;
    @Autowired
    private ProvedorGetter provedorGetter;
    @Autowired
    private ClienteGetter clienteGetter;

    public Servico byId(Long id){
        return repository.findById(id).orElseThrow(CommonExceptionSupplier.naoEncontrado("Servico", id));
    }

    public List<Servico> byNaoVistoPeloProvedor() {
        Provedor provedor = provedorGetter.byUsername(AuthService.getContextUser().getUsername());
        return repository.findByNaoVistoPeloProvedor(provedor.getId());
    }

    public List<Servico> byNaoVistoPeloCliente() {
        Cliente cliente = clienteGetter.byUsername(AuthService.getContextUser().getUsername());
        return repository.findByNaoVistoPeloCliente(cliente.getId());
    }

    public Page<Servico> byProvedor(Long idProvedor, Pageable pageable) {
        return this.repository.byProvedor(idProvedor, pageable);
    }
}
