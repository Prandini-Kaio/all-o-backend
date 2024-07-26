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
import org.springframework.stereotype.Component;

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

    public List<Profissao> findByProfissao(String profissao) {
        log.info(String.format("Consultando profissão %s.", profissao));

        return repository.findByFilter(profissao.toLowerCase());
    }

    public List<Profissao> findAll(){
        log.info("Consultando todas as profissões ativas do sistema.");

        return repository.findAtivos();
    }

    public boolean existsByNome(String nome) {
        log.info(String.format("Consultando existência de profissão %s", nome));

        Optional<Profissao> profissao = repository.findByNome(nome);

        return profissao.isPresent();
    }

    public List<Profissao> findDestaques() {
        log.info("Consultando profissões em destaque.");

        return repository.findDestaques();
    }

    public List<Profissao> findAleatorias() {

        log.info("Gerando profissões aleatórias.");

        return repository.findAleatorias();

    }
}
