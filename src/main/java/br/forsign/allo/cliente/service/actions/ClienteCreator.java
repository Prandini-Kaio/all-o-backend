package br.forsign.allo.cliente.service.actions;

import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.model.ClienteInput;
import br.forsign.allo.cliente.repository.ClienteRepository;
import br.forsign.allo.cliente.service.PerfilClienteService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ClienteCreator {

    @Resource
    private ClienteRepository repository;

    @Resource
    private ClienteValidator validator;

    @Resource
    private PerfilClienteService perfilClienteService;

    public Cliente create(ClienteInput input){

        validator.validarCreate(input);

        Cliente cliente = new Cliente();

        cliente.setNome(input.getNome());
        cliente.setEmail(input.getEmail());
        cliente.setTelefone(input.getTelefone());
        cliente.setCpfCnpj(input.getCpfCnpj());
        cliente.setAtivo(true);

        repository.save(cliente);

        perfilClienteService.create(cliente);

        return cliente;
    }
}
