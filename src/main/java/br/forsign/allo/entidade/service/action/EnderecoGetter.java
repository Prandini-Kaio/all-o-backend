package br.forsign.allo.entidade.service.action;

import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.entidade.domain.Endereco;
import br.forsign.allo.entidade.repository.EnderecoRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class EnderecoGetter {

    @Resource
    private EnderecoRepository repository;

    public Endereco byId(Long id){
        return repository.findById(id).orElseThrow(CommonExceptionSupplier.naoEncontrado("Endereço", id));
    }
}
