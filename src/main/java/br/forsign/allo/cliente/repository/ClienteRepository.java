package br.forsign.allo.cliente.repository;

import br.forsign.allo.cliente.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.ativo = true AND c.cpfCnpj = :cpf")
    Optional<Cliente> findByCpf(String cpf);

    @Query("SELECT c FROM Cliente c WHERE c.ativo = true")
    List<Cliente> findAtivos();
}

