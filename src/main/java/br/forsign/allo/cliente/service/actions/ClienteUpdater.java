package br.forsign.allo.cliente.service.actions;

import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.model.ClienteInput;
import br.forsign.allo.cliente.repository.ClienteRepository;
import br.forsign.allo.cliente.service.actions.contact.ContactCreator;
import br.forsign.allo.cliente.service.actions.document.DocumentCreator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ClienteUpdater {

    @Resource
    private ClienteRepository repository;

    @Resource
    private ClienteGetter getter;

    @Resource
    private ClienteValidator validator;

    public Cliente update(ClienteInput input){
        validator.validarUpdate(input);

        Cliente cliente = getter.byId(input.getId());

        cliente.setNome(input.getNome());
        cliente.setCpfCnpj(input.getCpfCnpj());
        cliente.setEmail(input.getEmail());
        cliente.setTelefone(input.getTelefone());
        cliente.setAtivo(true);

        return repository.save(cliente);
    }
}
