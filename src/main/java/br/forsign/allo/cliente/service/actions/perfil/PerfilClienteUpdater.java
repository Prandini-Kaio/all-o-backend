package br.forsign.allo.cliente.service.actions.perfil;

/*
 * @author prandini
 * created 5/25/24
 */

import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.domain.PerfilCliente;
import br.forsign.allo.cliente.model.ClienteInput;
import br.forsign.allo.cliente.repository.PerfilClienteRepository;
import br.forsign.allo.common.utils.CommonExceptionSupplier;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

@Component
@CommonsLog
public class PerfilClienteUpdater {

    @Resource
    private PerfilClienteRepository repository;

    public PerfilCliente update(ClienteInput cliente) {
        log.info(String.format("Atualizando perfil do cliente %s", cliente.getNome()));

        PerfilCliente perfil = repository.findByIdCliente(cliente.getId()).orElseThrow(CommonExceptionSupplier.naoEncontrado("PerfilCliente", cliente.getId()));

        perfil.setImagemPerfil(cliente.getImagem());

        return repository.save(perfil);
    }
}
