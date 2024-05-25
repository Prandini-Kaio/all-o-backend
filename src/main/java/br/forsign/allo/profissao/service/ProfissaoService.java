package br.forsign.allo.profissao.service;

/*
 * @author prandini
 * created 5/25/24
 */

import br.forsign.allo.profissao.converter.ProfissaoMapper;
import br.forsign.allo.profissao.domain.Profissao;
import br.forsign.allo.profissao.model.ProfissaoOutput;
import br.forsign.allo.profissao.service.action.ProfissaoGetter;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@CommonsLog
public class ProfissaoService {

    @Autowired
    private ProfissaoGetter getter;

    @Autowired
    private ProfissaoMapper mapper;

    public Page<ProfissaoOutput> findAll(Pageable pageable) {
        log.info("Consultando todas as profissões ativas do sistema.");

        return getter.findAll(pageable).map(mapper::toOutput);
    }
}
