package br.forsign.allo.provider.service;


import br.forsign.allo.provider.converter.ProviderConverter;
import br.forsign.allo.provider.model.ProviderInputDTO;
import br.forsign.allo.provider.model.ProviderOutputDTO;
import br.forsign.allo.provider.repository.ProviderRepository;
import br.forsign.allo.provider.service.action.ProviderCreator;
import br.forsign.allo.provider.service.action.ProviderGetter;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {
    /*
    @Resource
    private ProviderCreator creator;

    @Resource
    private ProviderGetter getter;

    @Resource
    private ProviderValidator validator;

    public ProviderOutputDTO create(ProviderInputDTO inputDTO) {
        validator.validarProviderCreate(inputDTO);
        return ProviderConverter.toOutput(creator.create(inputDTO));
    }

    public ProviderOutputDTO getByID(Long id) {
        return ProviderConverter.toOutput(getter.getByID(id));
    }*/
}