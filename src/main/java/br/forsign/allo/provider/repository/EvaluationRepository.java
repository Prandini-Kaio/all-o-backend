package br.forsign.allo.provider.repository;

import br.forsign.allo.provider.domain.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
}
