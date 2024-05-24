package br.forsign.allo.cliente.service.actions;

import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.repository.ClienteRepository;
import br.forsign.allo.common.utils.CommonExceptionSupplier;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component

public class ClienteGetter {

    @Resource
    private ClienteRepository repository;

    public List<Cliente> findAtivos(){
        return repository.findAtivos();
    }

    public Cliente byId(Long id){
        return repository.findById(id).orElseThrow(CommonExceptionSupplier.naoEncontrado("Cliente", id));
    }

    public Cliente byCpf(String cpf){
        return repository.findByCpf(cpf).orElseThrow(CommonExceptionSupplier.naoEncontrado("Cliente", cpf));
    }

    public boolean existsByCpf(String cpf){
        Optional<Cliente> cliente = repository.findByCpf(cpf);

        return cliente.isPresent();
    }

    public boolean existsById(Long id){
        Optional<Cliente> cliente = repository.findById(id);

        return cliente.isPresent();
    }
}