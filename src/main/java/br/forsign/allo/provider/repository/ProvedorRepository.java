package br.forsign.allo.provider.repository;


import br.forsign.allo.provider.domain.Provedor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvedorRepository extends JpaRepository<Provedor, Long> {

    @Query("SELECT p FROM Provedor p WHERE p.ativo = true AND LOWER(p.razaoSocial) LIKE CONCAT('%', :filtro, '%')")
    List<Provedor> findByFiltro(String filtro, Pageable pageable);
}
