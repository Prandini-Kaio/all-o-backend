package br.forsign.allo.entidade.service.action;

import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.entidade.domain.Endereco;
import br.forsign.allo.entidade.repository.EnderecoRepository;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

@Component
@CommonsLog
public class EnderecoGetter {

    @Resource
    private EnderecoRepository repository;

    public Endereco byId(Long id){
        log.info(String.format("Consultando endereço pelo id %s.", id));

        return repository.findById(id).orElseThrow(CommonExceptionSupplier.naoEncontrado("Endereço", id));
    }
}
