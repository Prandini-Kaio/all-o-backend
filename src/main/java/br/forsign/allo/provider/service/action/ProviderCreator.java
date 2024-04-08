package br.forsign.allo.provider.service.action;

import br.forsign.allo.provider.converter.ProviderConverter;
import br.forsign.allo.provider.domain.OperationHour;
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
        OperationHour operationHour = new OperationHour(inputDTO.getOperationHour());

        Provider provider = Provider.builder()
                .name(inputDTO.getName())
                .profile(ProviderConverter.getProfile(inputDTO.getProfile()))
                .rating(ProviderConverter.getRating(inputDTO.getRating()))
                .tipoPessoa(inputDTO.getTipoPessoa())
                .operationHour(operationHour)
                .build();

        return repository.save(provider);
    }
}
