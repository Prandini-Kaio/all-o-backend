package br.forsign.allo.profissao.service.action;

/*
 * @author prandini
 * created 5/25/24
 */

import br.forsign.allo.profissao.domain.Profissao;
import br.forsign.allo.profissao.repository.ProfissaoRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProfissaoGetter {

    @Resource
    private ProfissaoRepository repository;

    public Page<Profissao> findAll(Pageable pageable){
        return repository.findAtivos(pageable);
    }

    public boolean existsByNome(String nome) {
        Optional<Profissao> profissao = repository.findByNome(nome);

        return profissao.isPresent();
    }
}
