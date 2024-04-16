package br.forsign.allo.provider.service.action;

import br.forsign.allo.provider.repository.ProviderRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ProviderDeleter {

    @Resource
    ProviderRepository repository;

    public void delById(Long id){
        repository.deleteById(id);
    }
}
