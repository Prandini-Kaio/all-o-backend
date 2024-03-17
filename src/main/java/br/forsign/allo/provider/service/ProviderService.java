package br.forsign.allo.provider.service;


import br.forsign.allo.provider.converter.ProviderConverter;
import br.forsign.allo.provider.model.ProviderFilterDTO;
import br.forsign.allo.provider.model.ProviderInputDTO;
import br.forsign.allo.provider.model.ProviderOutputDTO;
import br.forsign.allo.provider.service.action.ProviderCreator;
import br.forsign.allo.provider.service.action.ProviderGetter;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {
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

    public Page<ProviderOutputDTO> getByFilter(ProviderFilterDTO filter, Pageable pageable) {
        return new PageImpl<>(getter.getByFilter(filter, pageable)).map(ProviderConverter::toOutput);
    }
}
