package br.forsign.allo.avaliacao.service.action;

/*
 * @author prandini
 * created 5/26/24
 */

import br.forsign.allo.avaliacao.domain.Avaliacao;
import br.forsign.allo.avaliacao.repository.AvaliacaoRepository;
import br.forsign.allo.common.utils.CommonExceptionSupplier;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class AvaliacaoGetter {

    @Resource
    private AvaliacaoRepository repository;

    public Avaliacao byId(Long id){
        return repository.findById(id).orElseThrow(CommonExceptionSupplier.naoEncontrado("Avaliacão", id));
    }

    public Page<Avaliacao> byProvedor(Long id, Pageable pageable) {
        return repository.byProvedor(id, pageable);
    }

    public List<Avaliacao> byProvedor(Long id) {
        return repository.byProvedor(id);
    }

    public Avaliacao byProvedorDestaque(Long provedorId) {
        Stream<Avaliacao> avaliacoes = this.repository.findByProvedor(provedorId);

        avaliacoes = avaliacoes.filter(a -> a.getNota() >= 4.5);

        return avaliacoes.findFirst().orElse(null);
    }
}
