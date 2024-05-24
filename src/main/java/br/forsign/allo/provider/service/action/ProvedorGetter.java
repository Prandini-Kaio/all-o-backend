package br.forsign.allo.provider.service.action;

import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.provider.domain.Provedor;
import br.forsign.allo.provider.model.ProvedorFilter;
import br.forsign.allo.provider.repository.ProvedorRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProviderGetter {

    @Resource
    private ProvedorRepository repository;

    public Provedor byId(Long id){
        return repository
                .findById(id)
                .orElseThrow(CommonExceptionSupplier.naoEncontrado("Provedor", id));
    }

    public List<Provedor> byFilter(ProvedorFilter filter, Pageable pageable){
        return repository.getByFilter(filter,pageable);
    }
}
