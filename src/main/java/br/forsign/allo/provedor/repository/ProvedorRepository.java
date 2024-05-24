package br.forsign.allo.provider.repository;


import br.forsign.allo.provider.domain.Provedor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvedorRepository extends JpaRepository<Provedor, Long> {

    @Query("SELECT p FROM Provedor p WHERE p.ativo = true AND LOWER(p.razaoSocial) LIKE CONCAT('%', LOWER(:filtro), '%')")
    List<Provedor> findByFiltro(String filtro, Pageable pageable);

    @Query("SELECT p FROM Provedor p WHERE p.cpfCnpj = :cpfCnpj")
    Optional<Provedor> findByCpfCnpj(String cpfCnpj);
}
