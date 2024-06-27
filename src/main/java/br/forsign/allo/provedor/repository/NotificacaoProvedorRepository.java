package br.forsign.allo.provedor.repository;

import br.forsign.allo.entidade.domain.Notificacao;
import br.forsign.allo.provedor.domain.NotificacaoProvedor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kaiooliveira
 * created 18/06/2024
 */

@Repository
public interface NotificacaoProvedorRepository extends JpaRepository<NotificacaoProvedor, Long>{


    @Query("SELECT n " +
            "FROM NotificacaoProvedor n " +
            "JOIN n.provedor p " +
            "JOIN p.usuario u " +
            "WHERE u.login = :login")
    List<NotificacaoProvedor> findByUsername(String login);
}
