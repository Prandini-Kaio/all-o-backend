package br.forsign.allo.provider.service.action;

import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.provider.domain.Provedor;
import br.forsign.allo.provider.repository.ProvedorRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public class ProvedorGetter {

    @Resource
    private ProvedorRepository repository;

    public Provedor byId(Long id){
        return repository
                .findById(id)
                .orElseThrow(CommonExceptionSupplier.naoEncontrado("Provedor", id));
    }

    public List<Provedor> byFilter(String razaoSocial, Pageable pageable){
        return repository.findByFiltro(razaoSocial, pageable);
    }
}
