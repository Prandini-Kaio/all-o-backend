package br.forsign.allo.cliente.service.actions;

import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.repository.ClienteRepository;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

@Component
@CommonsLog
public class ClienteDeleter {

    @Resource
    private ClienteRepository repository;

    @Resource
    private ClienteGetter getter;

    public void byId(Long id){
        log.info(String.format("Desativando cliente com id %s do sistema.", id));

        Cliente cliente = getter.byId(id);

        cliente.setAtivo(false);

        repository.save(cliente);
    }
}
