package br.forsign.allo.provedor.repository;

import br.forsign.allo.provedor.domain.PerfilProvedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilProvedorRepository extends JpaRepository<PerfilProvedor, Long> {

    @Query("SELECT p FROM PERFIL_PROVEDOR p JOIN p.provedor pr WHERE pr.id = :idProvedor AND pr.ativo = true")
    Optional<PerfilProvedor> findByIdProvedor(Long idProvedor);
}
