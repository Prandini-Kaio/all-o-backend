package br.forsign.allo.avaliacao.service.action;

/*
 * @author prandini
 * created 5/26/24
 */

import br.forsign.allo.avaliacao.domain.Avaliacao;
import br.forsign.allo.avaliacao.repository.AvaliacaoRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoGetter {

    @Resource
    private AvaliacaoRepository repository;

    public Page<Avaliacao> byProvedor(Long id, Pageable pageable) {
        return repository.byProvedor(id, pageable);
    }
}
