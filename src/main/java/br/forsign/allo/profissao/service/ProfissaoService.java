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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@CommonsLog
public class ProfissaoService {

    @Resource
    private ProfissaoGetter getter;

    @Resource
    private ProfissaoCreator creator;

    @Resource
    private ProfissaoMapper mapper;

    public Page<ProfissaoOutput> findAll(Pageable pageable) {
        log.info("Consultando todas as profissões ativas do sistema.");

        return getter.findAll(pageable).map(mapper::toOutput);
    }

    public ProfissaoOutput create(ProfissaoInput input) {
        log.info("Cadastrando um profissão.");

        return mapper.toOutput(creator.create(input));
    }
}
