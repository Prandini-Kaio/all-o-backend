package br.forsign.allo.cliente.service.actions.perfil;

/*
 * @author prandini
 * created 5/24/24
 */

import br.forsign.allo.cliente.domain.PerfilCliente;
import br.forsign.allo.cliente.model.PerfilClienteOutput;
import br.forsign.allo.cliente.repository.PerfilClienteRepository;
import br.forsign.allo.common.utils.CommonExceptionSupplier;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PerfilClienteGetter {

    @Resource
    private PerfilClienteRepository repository;

    public PerfilCliente byCliente(Long idCliente) {
        return repository.findByIdCliente(idCliente).orElseThrow(CommonExceptionSupplier.naoEncontrado("PerfilCliente", idCliente));
    }

    public Page<PerfilCliente> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
