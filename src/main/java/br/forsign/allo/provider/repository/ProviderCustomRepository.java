package br.forsign.allo.provider.repository;


import br.forsign.allo.provider.domain.Provider;
import br.forsign.allo.provider.model.ProviderFilterDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProviderCustomRepository {
    List<Provider> getByFilter(ProviderFilterDTO filter, Pageable pageable);
}
