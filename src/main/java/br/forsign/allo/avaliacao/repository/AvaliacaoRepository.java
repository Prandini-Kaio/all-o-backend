package br.forsign.allo.avaliacao.repository;


import br.forsign.allo.avaliacao.domain.Avaliacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query("SELECT a FROM Avaliacao a JOIN a.provedor p WHERE p.id = :id AND p.ativo = true")
    Page<Avaliacao> byProvedor(Long id, Pageable pageable);

    @Query("SELECT a FROM Avaliacao a JOIN a.provedor p WHERE p.id = :id AND p.ativo = true")
    List<Avaliacao> byProvedor(Long id);

    @Query("SELECT a FROM Avaliacao a JOIN a.provedor p WHERE p.id = :id AND p.ativo = true")
    Stream<Avaliacao> findByProvedor(Long id);

}
