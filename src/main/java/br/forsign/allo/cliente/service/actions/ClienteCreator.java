package br.forsign.allo.cliente.service.actions;

import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.model.ClienteInput;
import br.forsign.allo.cliente.repository.ClienteRepository;
import br.forsign.allo.cliente.service.PerfilClienteService;
import br.forsign.allo.common.utils.CpfCnpjUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;

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
        cliente.setCpfCnpj(CpfCnpjUtils.removeMascara(input.getCpfCnpj()));
        cliente.setProvedoresFavoritados(new HashSet<>());
        cliente.setAtivo(true);
        cliente.setDtRegistro(LocalDate.now());

        repository.save(cliente);

        perfilClienteService.create(input, cliente);

        return cliente;
    }
}
