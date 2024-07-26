package br.forsign.allo.provedor.service.action;

import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.repository.ProvedorRepository;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

@Component
@CommonsLog
public class ProvedorDeleter {

    @Resource
    private ProvedorRepository repository;

    @Resource
    private ProvedorGetter getter;

    public void byId(Long id){
        log.info(String.format("Desativando provedor com id %s.", id));

        Provedor provedor = getter.byId(id);

        provedor.setAtivo(false);

        repository.save(provedor);
    }
}
