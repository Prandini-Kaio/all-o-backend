package br.forsign.allo.provider.repository;


import br.forsign.allo.provider.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long>, ProviderCustomRepository {

}
