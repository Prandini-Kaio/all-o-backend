package br.forsign.allo.cliente.service.actions;

import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.repository.ClienteRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ClienteDeleter {

    @Resource
    private ClienteRepository repository;

    @Resource
    private ClienteGetter getter;

    public void byId(Long id){
        Cliente cliente = getter.byId(id);
        cliente.setAtivo(false);
        repository.save(cliente);
    }
}