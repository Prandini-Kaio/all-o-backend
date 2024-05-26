package br.forsign.allo.cliente.service;

import br.forsign.allo.cliente.converter.ClienteConverter;
import br.forsign.allo.cliente.converter.ClienteMapper;
import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.model.ClienteInput;
import br.forsign.allo.cliente.model.ClienteOuput;
import br.forsign.allo.cliente.repository.ClienteRepository;
import br.forsign.allo.cliente.service.actions.ClienteCreator;
import br.forsign.allo.cliente.service.actions.ClienteDeleter;
import br.forsign.allo.cliente.service.actions.ClienteGetter;
import br.forsign.allo.cliente.service.actions.ClienteUpdater;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@CommonsLog
@Service
public class ClienteService {

    @Resource
    private ClienteCreator creator;

    @Resource
    private ClienteGetter getter;

    @Resource
    private ClienteDeleter deleter;

    @Resource
    private ClienteUpdater updater;

    @Resource
    private ClienteMapper mapper;


    public List<ClienteOuput> findAll(){
        log.info("Consultando clientes ativos no sistema.");

        List<Cliente> clientes = getter.findAtivos();

        return clientes.stream().map(mapper::toOutput).toList();
    }

    public ClienteOuput create(ClienteInput input){
        log.info("Cadastrando cliente no sistema.");

        return this.mapper.toOutput(this.creator.create(input));
    }

    public ClienteOuput update(ClienteInput input){
        log.info("Atualizando cadastro do cliente no sistema.");

        return this.mapper.toOutput(updater.update(input));
    }

    public void delete(Long id){
        log.info("Deletando cliente no sistema.");

        deleter.byId(id);
    }

    public ClienteOuput favoritar(Long idCliente, Long idProvedor) {
        return this.mapper.toOutput(this.updater.favoritar(idCliente, idProvedor));
    }
}