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
import jakarta.transaction.Transactional;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@CommonsLog
public class AvaliacaoService {

    @Resource
    private AvaliacaoGetter getter;

    @Resource
    private AvaliacaoCreator creator;

    @Resource
    private AvaliacaoMapper mapper;


    public List<AvaliacaoOutput> byProvedor(Long id) {
        log.info("Iniciando pesquisa de avaliação pelo ID do provedor: " + id);

        return this.getter.byProvedor(id).stream().map(mapper::toOutput).collect(Collectors.toList());
    }

    public AvaliacaoOutput avaliar(AvaliacaoInput input) {
        log.info("Iniciando cadastro de avaliação.");

        return this.mapper.toOutput(this.creator.avaliar(input));
    }

    @Transactional
    public AvaliacaoOutput byProvedorDestaque(Long provedorId) {
        log.info("Iniciando consulta de avaliação em destaque.");

        return this.mapper.toOutput(this.getter.byProvedorDestaque(provedorId));
    }
}
