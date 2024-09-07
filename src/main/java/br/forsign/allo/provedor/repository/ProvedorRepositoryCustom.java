package br.forsign.allo.provedor.repository;

import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.ProvedorFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author kaiooliveira
 * created 16/06/2024
 */
public interface ProvedorRepositoryCustom {

    Page<Provedor> byFilter(String razaoSocial, String profissao, Pageable pageable);

    List<Provedor> byFilter(ProvedorFilter filter);

    List<Provedor> mostRelevant(ProvedorFilter filter);
}
