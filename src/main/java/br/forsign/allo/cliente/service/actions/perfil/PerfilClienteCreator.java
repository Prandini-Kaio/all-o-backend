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
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

@Component
@CommonsLog
public class PerfilClienteCreator {

    @Resource
    private PerfilClienteRepository repository;

    public PerfilCliente create(ClienteInput input, Cliente cliente) {
        log.info(String.format("Cadastrando perfil do cliente %s.", cliente.getNome()));

        PerfilCliente perfilCliente = new PerfilCliente();

        perfilCliente.setCliente(cliente);
        perfilCliente.setImagemPerfil(input.getImagem());

        return repository.save(perfilCliente);
    }
}
