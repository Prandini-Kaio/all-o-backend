package br.forsign.allo.provider.service.action;

import br.forsign.allo.provider.repository.ProvedorRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ProviderDeleter {

    @Resource
    private ProvedorRepository repository;

    public void byId(Long id){
        repository.deleteById(id);
    }
}
