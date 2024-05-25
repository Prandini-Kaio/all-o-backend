package br.forsign.allo.cliente.service;

/*
 * @author prandini
 * created 5/24/24
 */

import br.forsign.allo.cliente.converter.PerfilClienteMapper;
import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.model.PerfilClienteOutput;
import br.forsign.allo.cliente.service.actions.perfil.PerfilClienteCreator;
import br.forsign.allo.cliente.service.actions.perfil.PerfilClienteGetter;
import br.forsign.allo.cliente.service.actions.perfil.PerfilClienteUpdater;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PerfilClienteMapper mapper;

    public Page<PerfilClienteOutput> findAll(Pageable pageable){
        return getter.findAll(pageable).map(mapper::toOuput);
    }

    public PerfilClienteOutput create(Cliente cliente){
        return mapper.toOuput(creator.create(cliente));
    }

    public PerfilClienteOutput update(Cliente cliente){
        return mapper.toOuput(updater.update(cliente));
    }
}
