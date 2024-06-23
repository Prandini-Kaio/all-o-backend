package br.forsign.allo.servico.service.action;

import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.servico.domain.Servico;
import br.forsign.allo.servico.service.repository.ServicoRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author kaiooliveira
 * created 23/06/2024
 */

@Component
public class ServicoGetter {

    @Resource
    private ServicoRepository repository;

    public Servico byId(Long id){
        return repository.findById(id).orElseThrow(CommonExceptionSupplier.naoEncontrado("Servico", id));
    }
}
