package br.forsign.allo.cliente.service.actions.perfil;

/*
 * @author prandini
 * created 5/25/24
 */

import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.domain.PerfilCliente;
import br.forsign.allo.cliente.model.ClienteInput;
import br.forsign.allo.cliente.repository.PerfilClienteRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class PerfilClienteCreator {

    @Resource
    private PerfilClienteRepository repository;

    public PerfilCliente create(ClienteInput input, Cliente cliente) {
        PerfilCliente perfilCliente = new PerfilCliente();

        perfilCliente.setCliente(cliente);
        perfilCliente.setNome(cliente.getNome());
        perfilCliente.setEmail(cliente.getEmail());
        perfilCliente.setImagemPerfil(input.getImagem());

        return repository.save(perfilCliente);
    }
}
