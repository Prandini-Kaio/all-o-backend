package br.forsign.allo.provider.service.action;

import br.forsign.allo.provider.domain.Provider;
import br.forsign.allo.provider.model.ProviderInputDTO;
import br.forsign.allo.provider.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProviderCreator {

    @Autowired
    private ProviderRepository repository;

    public Provider create(ProviderInputDTO inputDTO){

       Provider provider = new Provider();

       provider.setName(inputDTO.getName());
       provider.setTipoPessoa(inputDTO.getTipoPessoa());

        return repository.save(provider);
    }
}
