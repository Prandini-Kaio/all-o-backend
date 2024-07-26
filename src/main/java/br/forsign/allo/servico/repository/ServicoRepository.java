package br.forsign.allo.servico.repository;

import br.forsign.allo.servico.domain.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author kaiooliveira
 * created 23/06/2024
 */

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

    @Query("SELECT s FROM Servico s WHERE s.servicoVisto = FALSE AND s.provedor.id = :idProvedor")
    List<Servico> findByNaoVistoPeloProvedor(Long idProvedor);

    @Query("SELECT s FROM Servico s WHERE s.servicoRealizado AND s.avaliacao.id IS NULL AND s.cliente.id = :idCliente ")
    List<Servico> findByNaoVistoPeloCliente(Long idCliente);

    @Query("SELECT COUNT(s) FROM Servico s JOIN s.provedor p ON s.provedor.id = p.id WHERE p.id = :idProvedor")
    int getTotalAvaliacoes(Long idProvedor);

    @Query("SELECT s FROM Servico s JOIN s.provedor p ON s.provedor.id = p.id WHERE p.id = :idProvedor AND s.avaliacao.id IS NOT NULL")
    List<Servico> byProvedor(Long idProvedor);

    @Query("SELECT s " +
            "FROM Servico s " +
            "JOIN s.cliente c " +
            "JOIN c.usuario u " +
            "WHERE u.login = :cliente " +
            "AND s.id = :idServico " +
            "AND s.servicoRealizado = TRUE")
    Optional<Servico> findByClienteAndId(String cliente, Long idServico);

    @Query("SELECT s " +
            "FROM Servico s " +
            "JOIN s.provedor p " +
            "JOIN p.usuario u " +
            "WHERE u.login = :provedor " +
            "AND s.id = :idServico ")
    Optional<Servico> findByProvedorAndId(String provedor, Long idServico);
}
