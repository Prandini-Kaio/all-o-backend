package br.forsign.allo.provedor.repository;

import br.forsign.allo.provedor.domain.Provedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author kaiooliveira
 * created 16/06/2024
 */
public interface ProvedorRepositoryCustom {

    Page<Provedor> byFilter(String razaoSocial, String profissao, Pageable pageable);

}
