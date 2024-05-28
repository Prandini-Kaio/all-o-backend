package br.forsign.allo.avaliacao.service;

/*
 * @author prandini
 * created 5/26/24
 */

import br.forsign.allo.avaliacao.converter.AvaliacaoMapper;
import br.forsign.allo.avaliacao.model.AvaliacaoInput;
import br.forsign.allo.avaliacao.model.AvaliacaoOutput;
import br.forsign.allo.avaliacao.service.action.AvaliacaoCreator;
import br.forsign.allo.avaliacao.service.action.AvaliacaoGetter;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@CommonsLog
public class AvaliacaoService {

    @Resource
    private AvaliacaoGetter getter;

    @Resource
    private AvaliacaoCreator creator;

    @Resource
    private AvaliacaoMapper mapper;


    public Page<AvaliacaoOutput> byProvedor(Long id, Pageable pageable) {
        return this.getter.byProvedor(id,pageable).map(mapper::toOutput);
    }

    public AvaliacaoOutput avaliar(AvaliacaoInput input) {
        log.info("Cadastrando avaliação.");

        return this.mapper.toOutput(this.creator.avaliar(input));
    }

    public AvaliacaoOutput byProvedorDestaque(Long provedorId) {
        log.info("Consultando avaliação em destaque");
        log.debug("Funcionalidade mockada, retornando avaliação randomizada");

        return this.mapper.toOutput(this.getter.byProvedorDestaque(provedorId));
    }
}
