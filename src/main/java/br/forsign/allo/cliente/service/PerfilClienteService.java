package br.forsign.allo.cliente.service;

/*
 * @author prandini
 * created 5/24/24
 */

import br.forsign.allo.cliente.converter.PerfilClienteMapper;
import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.model.ClienteInput;
import br.forsign.allo.cliente.model.PerfilClienteOutput;
import br.forsign.allo.cliente.service.actions.perfil.PerfilClienteCreator;
import br.forsign.allo.cliente.service.actions.perfil.PerfilClienteGetter;
import br.forsign.allo.cliente.service.actions.perfil.PerfilClienteUpdater;
import br.forsign.allo.provedor.model.PerfilProvedorOutput;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PerfilClienteService {

    @Resource
    private PerfilClienteGetter getter;

    @Resource
    private PerfilClienteCreator creator;

    @Resource
    private PerfilClienteUpdater updater;

    @Resource
    private PerfilClienteMapper mapper;

    public PerfilClienteOutput getByClienteId(Long idCliente){
        return mapper.toOutput(getter.byClienteId(idCliente));
    }

    public Page<PerfilClienteOutput> findAll(Pageable pageable){
        return getter.findAll(pageable).map(mapper::toOutput);
    }

    public PerfilClienteOutput create(ClienteInput input, Cliente cliente){
        return mapper.toOutput(creator.create(input, cliente));
    }

    public PerfilClienteOutput update(Cliente cliente){
        return mapper.toOutput(updater.update(cliente));
    }
}
