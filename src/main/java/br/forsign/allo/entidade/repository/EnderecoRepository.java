package br.forsign.allo.entidade.repository;

import br.forsign.allo.entidade.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kaiooliveira
 * created 16/06/2024
 */

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
