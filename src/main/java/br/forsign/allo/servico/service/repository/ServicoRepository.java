package br.forsign.allo.servico.service.repository;

import br.forsign.allo.servico.domain.Servico;
import jakarta.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kaiooliveira
 * created 23/06/2024
 */

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
