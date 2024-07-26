package br.forsign.allo.profissao.repository;

import br.forsign.allo.profissao.domain.Profissao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfissaoRepository extends JpaRepository<Profissao, Long> {

    @Query("SELECT p FROM Profissao p WHERE p.ativo = true")
    List<Profissao> findAtivos();

    @Query("SELECT p " +
            "FROM Profissao p " +
            "WHERE p.ativo = true " +
            "AND p.id = :id ")
    Optional<Profissao> findById(Long id);

    @Query("SELECT p " +
            "FROM Profissao p " +
            "WHERE p.ativo = true " +
            "AND p.nome = :nome")
    Optional<Profissao> findByNome(String nome);

    @Query("SELECT p " +
            "FROM Profissao p " +
            "WHERE LOWER(p.nome) LIKE %:prof%")
    List<Profissao> findByFilter(@Param("prof") String profissao);

    @Query("SELECT p.profissao " +
            "FROM Servico s " +
            "JOIN s.provedor p " +
            "JOIN p.profissao profissao " +
            "WHERE s.servicoRealizado = true " +
            "GROUP BY profissao.id, profissao.nome, profissao.nomeIcone, profissao.suggestion, profissao.ativo " +
            "ORDER BY COUNT(s) DESC " +
            "LIMIT 6")
    List<Profissao> findDestaques();

    @Query("SELECT p " +
            "FROM Profissao p " +
            "ORDER BY RANDOM() " +
            "LIMIT 6")
    List<Profissao> findAleatorias();
}
