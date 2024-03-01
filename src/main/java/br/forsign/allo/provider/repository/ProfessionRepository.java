package br.forsign.allo.provider.repository;

import br.forsign.allo.provider.domain.Profession;
import br.forsign.allo.user.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, Long> {

}
