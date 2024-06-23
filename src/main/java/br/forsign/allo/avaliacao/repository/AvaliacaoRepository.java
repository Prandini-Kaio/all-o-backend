package br.forsign.allo.avaliacao.repository;


import br.forsign.allo.avaliacao.domain.Avaliacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

}
