package br.forsign.allo.profissao.service.action;

/*
 * @author prandini
 * created 5/25/24
 */

import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.profissao.domain.Profissao;
import br.forsign.allo.profissao.repository.ProfissaoRepository;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@CommonsLog
public class ProfissaoGetter {

    @Resource
    private ProfissaoRepository repository;

    public Profissao byIdAtivo(Long id) {
        log.info(String.format("Consultando profissão ativa com id %s.", id));

        return repository.findById(id).orElseThrow(CommonExceptionSupplier.naoEncontrado("Profissão", id));
    }

    public Page<Profissao> findAll(Pageable pageable){
        log.info("Consultando todas as profissões ativas do sistema.");

        return repository.findAtivos(pageable);
    }

    public boolean existsByNome(String nome) {
        Optional<Profissao> profissao = repository.findByNome(nome);

        return profissao.isPresent();
    }
}
