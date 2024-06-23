package br.forsign.allo.profissao.service.action;

/*
 * @author prandini
 * created 5/25/24
 */

import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.profissao.domain.Profissao;
import br.forsign.allo.profissao.repository.ProfissaoRepository;
import br.forsign.allo.provedor.domain.Provedor;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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

    public List<Profissao> byIdAtivo(List<Long> ids) {
        log.info("Consultando profissões ativas com ids informados.");

        List<Profissao> profissoes = new ArrayList<>();

        for(Long id : ids){
            profissoes.add(byIdAtivo(id));
        }

        return profissoes;
    }

    public List<Profissao> findByFilter(String profissao) {
        return repository.findByFilter(profissao.toLowerCase());
    }

    public List<Profissao> findAll(){
        log.info("Consultando todas as profissões ativas do sistema.");

        return repository.findAtivos();
    }

    public boolean existsByNome(String nome) {
        Optional<Profissao> profissao = repository.findByNome(nome);

        return profissao.isPresent();
    }
}
