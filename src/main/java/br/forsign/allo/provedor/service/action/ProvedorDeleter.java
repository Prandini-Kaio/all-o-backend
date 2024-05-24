package br.forsign.allo.provider.service.action;

import br.forsign.allo.provider.domain.Provedor;
import br.forsign.allo.provider.repository.ProvedorRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ProvedorDeleter {

    @Resource
    private ProvedorRepository repository;

    @Resource
    private ProvedorGetter getter;

    public void byId(Long id){
        Provedor provedor = getter.byId(id);
        provedor.setAtivo(false);
        repository.save(provedor);
    }
}
