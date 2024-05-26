package br.forsign.allo.cliente.service.actions;

import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.model.ClienteInput;
import br.forsign.allo.cliente.repository.ClienteRepository;
import br.forsign.allo.cliente.service.actions.perfil.PerfilClienteUpdater;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.service.action.ProvedorGetter;
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

    @Resource
    private PerfilClienteUpdater updater;

    @Resource
    private ProvedorGetter provedorGetter;


    public Cliente update(ClienteInput input){
        validator.validarUpdate(input);

        Cliente cliente = getter.byId(input.getId());

        cliente.setNome(input.getNome());
        cliente.setCpfCnpj(input.getCpfCnpj());
        cliente.setEmail(input.getEmail());
        cliente.setTelefone(input.getTelefone());
        cliente.setAtivo(true);

        updater.update(cliente);

        return repository.save(cliente);
    }

    public Cliente favoritar(Long idCliente, Long idProvedor) {

        Cliente cliente = getter.byId(idCliente);
        Provedor provedor = provedorGetter.byId(idProvedor);

        if(cliente.getProvedoresFavoritados().contains(provedor))
            cliente.getProvedoresFavoritados().remove(provedor);
        else
            cliente.getProvedoresFavoritados().add(provedor);

        return this.repository.save(cliente);
    }
}
