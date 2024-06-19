package br.forsign.allo.cliente.repository;

/*
 * @author prandini
 * created 5/24/24
 */

import br.forsign.allo.cliente.domain.PerfilCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilClienteRepository extends JpaRepository<PerfilCliente, Long> {

    @Query("SELECT prf FROM PERFIL_CLIENTE prf JOIN prf.cliente c WHERE c.id = :idCliente AND c.ativo = true")
    Optional<PerfilCliente> findByIdCliente(Long idCliente);
}
