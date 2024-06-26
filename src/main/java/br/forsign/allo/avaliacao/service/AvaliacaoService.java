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
import br.forsign.allo.usuario.domain.Usuario;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        return this.getter.byProvedor(id).stream().map(mapper::toOutput).collect(Collectors.toList());
    }

    public AvaliacaoOutput avaliar(AvaliacaoInput input) {
        log.info("Cadastrando avaliação.");

        return this.mapper.toOutput(this.creator.avaliar(input));
    }

    @Transactional
    public AvaliacaoOutput byProvedorDestaque(Long provedorId) {
        log.info("Consultando avaliação em destaque");
        log.debug("Funcionalidade mockada, retornando avaliação randomizada");

        return this.mapper.toOutput(this.getter.byProvedorDestaque(provedorId));
    }
}
