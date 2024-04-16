package br.forsign.allo.provider.service.action;

import br.forsign.allo.provider.domain.Provider;
import br.forsign.allo.provider.model.ProviderFilterDTO;
import br.forsign.allo.provider.repository.ProviderRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProviderGetter {

    @Resource
    private ProviderRepository repository;

    public List<Provider> getByFilter(ProviderFilterDTO filter, Pageable pageable){
        return repository.getByFilter(filter,pageable);
    }
}
