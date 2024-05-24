package br.forsign.allo.cliente.service;

import br.forsign.allo.cliente.converter.ClienteConverter;
import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.model.ClienteOuput;
import br.forsign.allo.cliente.model.ClienteInput;
import br.forsign.allo.cliente.repository.ClienteRepository;
import br.forsign.allo.cliente.service.actions.*;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Resource
    private ClienteCreator creator;

    @Resource
    private ClienteGetter getter;

    @Resource
    private ClienteDeleter deleter;

    @Resource
    private ClienteUpdater updater;

    @Resource
    private ClienteValidator validator;


    public List<ClienteOuput> findAll(){
        List<Cliente> clientes = getter.findAtivos();

        return clientes.stream().map(ClienteConverter::toOutput).toList();
    }

    public ClienteOuput create(ClienteInput input){
        return ClienteConverter.toOutput(creator.create(input));
    }

    public ClienteOuput update(ClienteInput input){
        return ClienteConverter.toOutput(updater.update(input));
    }

    public void delete(Long id){
        deleter.byId(id);
    }
}