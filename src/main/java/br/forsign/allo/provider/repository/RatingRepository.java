package br.forsign.allo.provider.repository;

import br.forsign.allo.provider.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
