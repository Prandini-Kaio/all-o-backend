package br.forsign.allo.profissao.service;

/*
 * @author prandini
 * created 5/25/24
 */

import br.forsign.allo.profissao.converter.ProfissaoMapper;
import br.forsign.allo.profissao.model.ProfissaoInput;
import br.forsign.allo.profissao.model.ProfissaoOutput;
import br.forsign.allo.profissao.service.action.ProfissaoCreator;
import br.forsign.allo.profissao.service.action.ProfissaoGetter;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CommonsLog
public class ProfissaoService {

    @Resource
    private ProfissaoGetter getter;

    @Resource
    private ProfissaoCreator creator;

    @Resource
    private ProfissaoMapper mapper;

    public List<ProfissaoOutput> findAll() {
        log.info("Iniciando consulta de todas as profissões ativas do sistema.");

        return getter.findAll().stream().map(mapper::toOutput).toList();
    }

    public List<ProfissaoOutput> findByProfissao(String profissao) {
        log.info(String.format("Iniciando de profissao %s", profissao));

        return getter.findByProfissao(profissao).stream().map(mapper::toOutput).toList();
    }

    public ProfissaoOutput findById(Long id) {
        log.info(String.format("Iniciando consulta uma profissão com id %s.", id));

        return mapper.toOutput(getter.byIdAtivo(id));
    }

    public ProfissaoOutput create(ProfissaoInput input) {
        log.info(String.format("Iniciando o cadastro da profissão %s.", input.getNome()));

        return mapper.toOutput(creator.create(input));
    }

    public ProfissaoOutput createSugestion(String suggestion){
        log.info(String.format("Iniciando cadastro de sugestão de profissão %s.", suggestion));

        return mapper.toOutput(creator.createSugestion(suggestion));
    }

    public List<ProfissaoOutput> findDestaques() {
        log.info("Iniciando consulta de todas as profissões em destaque.");

        return getter.findDestaques().stream().map(mapper::toOutput).toList();
    }

    public List<ProfissaoOutput> findAleatorias() {
        log.info("Iniciando consulta de profissões aleatórias.");

        return getter.findAleatorias().stream().map(mapper::toOutput).toList();
    }
}
