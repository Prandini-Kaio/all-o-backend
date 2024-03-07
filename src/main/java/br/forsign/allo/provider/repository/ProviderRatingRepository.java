package br.forsign.allo.provider.repository;

import br.forsign.allo.provider.domain.ProviderRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRatingRepository extends JpaRepository<ProviderRating, Long> {
}
