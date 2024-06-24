package br.forsign.allo.servico.repository;

import br.forsign.allo.servico.domain.Servico;
import jakarta.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Query("SELECT COUNT(s) FROM Servico s JOIN s.provedor p WHERE p.id = :idProvedor")
    int getTotalAvaliacoes(Long idProvedor);
}
