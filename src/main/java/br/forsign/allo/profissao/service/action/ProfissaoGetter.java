package br.forsign.allo.profissao.service.action;

/*
 * @author prandini
 * created 5/25/24
 */

import br.forsign.allo.profissao.domain.Profissao;
import br.forsign.allo.profissao.repository.ProfissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ProfissaoGetter {

    @Autowired
    private ProfissaoRepository repository;

    public Page<Profissao> findAll(Pageable pageable){
        return repository.findAtivos(pageable);
    }
}
