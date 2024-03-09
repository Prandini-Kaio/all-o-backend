package br.forsign.allo.provider.service;


import br.forsign.allo.provider.converter.ProviderConverter;
import br.forsign.allo.provider.model.ProviderInputDTO;
import br.forsign.allo.provider.model.ProviderOutputDTO;
import br.forsign.allo.provider.repository.ProviderRepository;
import br.forsign.allo.provider.service.action.ProviderCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {
    @Autowired
    private ProviderCreator creator;

    public ProviderOutputDTO create(ProviderInputDTO inputDTO) {
        //VALIDATOR
        return ProviderConverter.toOutput(creator.create(inputDTO));
    }
}