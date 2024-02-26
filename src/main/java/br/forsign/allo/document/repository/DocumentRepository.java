package br.forsign.allo.document.repository;

import br.forsign.allo.document.domain.Documents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Documents, Long> {

}
