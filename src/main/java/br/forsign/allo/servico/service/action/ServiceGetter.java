package br.forsign.allo.servico.service.action;

import br.forsign.allo.servico.repository.ServicoRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author kaiooliveira
 * created 23/06/2024
 */

@Component
public class ServiceGetter {

    @Resource
    private ServicoRepository repository;

    public int getTotalAvaliacoes(Long idProvedor){
        return repository.getTotalAvaliacoes(idProvedor);
    }
}
