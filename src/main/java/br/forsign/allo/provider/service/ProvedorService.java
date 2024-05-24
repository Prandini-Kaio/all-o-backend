package br.forsign.allo.provider.service;


import br.forsign.allo.provider.converter.ProviderConverter;
import br.forsign.allo.provider.model.ProvedorFilter;
import br.forsign.allo.provider.model.ProviderInputDTO;
import br.forsign.allo.provider.model.ProviderOutputDTO;
import br.forsign.allo.provider.service.action.ProviderCreator;
import br.forsign.allo.provider.service.action.ProviderDeleter;
import br.forsign.allo.provider.service.action.ProviderGetter;
import br.forsign.allo.provider.service.action.ProviderUpdater;
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
    private ProviderUpdater updater;

    @Resource
    private ProviderDeleter deleter;

    @Resource
    private ProviderValidator validator;

    public ProviderOutputDTO create(ProviderInputDTO inputDTO) {
        validator.validarProviderCreate(inputDTO);
        return ProviderConverter.toProviderDTO(creator.create(inputDTO));
    }

    public Page<ProviderOutputDTO> getByFilter(ProvedorFilter filter, Pageable pageable) {
        return new PageImpl<>(getter.getByFilter(filter, pageable)).map(ProviderConverter::toProviderDTO);
    }

    public ProviderOutputDTO updateById(ProviderInputDTO inputDTO){
        return ProviderConverter.toProviderDTO(updater.update(inputDTO));
    }

    public void delById(Long id){
        deleter.delById(id);
    }
}
