package br.forsign.allo.provider.service.action;

import br.forsign.allo.provider.domain.Provider;
import br.forsign.allo.provider.repository.ProviderRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ProviderGetter {

    @Resource
    private ProviderRepository repository;

    public Provider getByID(Long id){
        return repository.findById(id).orElse(null);
    }
}
