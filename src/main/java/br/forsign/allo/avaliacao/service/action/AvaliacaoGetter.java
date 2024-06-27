package br.forsign.allo.avaliacao.service.action;

/*
 * @author prandini
 * created 5/26/24
 */

import br.forsign.allo.avaliacao.domain.Avaliacao;
import br.forsign.allo.avaliacao.repository.AvaliacaoRepository;
import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.provedor.domain.Provedor;
import jakarta.annotation.Resource;
import org.apache.catalina.mapper.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AvaliacaoGetter {

    @Resource
    private AvaliacaoRepository repository;

    public Avaliacao byId(Long id){
        return repository.findById(id).orElseThrow(CommonExceptionSupplier.naoEncontrado("Avaliacão", id));
    }

    public List<Avaliacao> findAll() {
        return repository.findAll();
    }

    public Avaliacao byProvedorDestaque(Long provedorId) {
        List<Avaliacao> avaliacoes = this.repository.findAll();

        avaliacoes = avaliacoes.stream().filter(a -> a.getNota() >= 4.5).collect(Collectors.toList());

        return avaliacoes.stream().filter(a -> a.getNota() >= 4.5).findFirst().orElse(null);
    }

    public List<Avaliacao> byProvedor(Long idProvedor) {
        return repository.findByProvedor(idProvedor);
    }
}
