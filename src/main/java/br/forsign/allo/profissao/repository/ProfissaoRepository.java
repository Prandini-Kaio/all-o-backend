package br.forsign.allo.profissao.repository;

import br.forsign.allo.profissao.domain.Profissao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissaoRepository extends JpaRepository<Profissao, Long> {

    @Query("SELECT p FROM Profissao p WHERE p.ativo = true")
    Page<Profissao> findAtivos(Pageable pageable);
}
