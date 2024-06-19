package br.forsign.allo.provedor.repository;

import br.forsign.allo.provedor.domain.NotificacaoProvedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kaiooliveira
 * created 18/06/2024
 */

@Repository
public interface NotificacaoProvedorRepository extends JpaRepository<NotificacaoProvedor, Long>{
}
