package br.forsign.allo.provedor.repository;


import br.forsign.allo.provedor.domain.Provedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvedorRepository extends JpaRepository<Provedor, Long>, ProvedorRepositoryCustom {

    @Query("SELECT p FROM Provedor p WHERE p.cpfCnpj = :cpfCnpj")
    Optional<Provedor> findByCpfCnpj(String cpfCnpj);

    @Query("SELECT p FROM Provedor p WHERE p.ativo = true")
    Optional<Page<Provedor>> findAtivos(Pageable pageable);

    @Query("SELECT p FROM Provedor p JOIN Servico s ON s.provedor.id = s.id WHERE s.provedor.ativo = true GROUP BY p ORDER BY AVG(s.avaliacao.nota) DESC LIMIT 5")
    List<Provedor> findMelhoresAvaliacoes();

    //@Query("SELECT p FROM Provedor p JOIN Avaliacao a on a.provedor.id = p.id WHERE p.ativo = true GROUP BY p ORDER BY AVG(a.nota) DESC LIMIT 5")
    //List<Provedor> findMelhoresAvaliacoes();


    @Query("SELECT p FROM Provedor p JOIN p.profissoes prfs WHERE prfs.id = :idProfissao")
    List<Provedor> findByProfissoesId(Long idProfissao);

    @Query("SELECT p FROM Provedor p JOIN p.usuario u WHERE u.login = :username")
    Optional<Provedor> byUsername(String username);
}
