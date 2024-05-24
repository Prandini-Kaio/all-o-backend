package br.forsign.allo.provider.repository;


import br.forsign.allo.provider.domain.Provedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provedor, Long>, ProviderCustomRepository {

}
