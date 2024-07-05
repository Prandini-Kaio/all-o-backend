package br.forsign.allo.entidade.service;

import br.forsign.allo.entidade.converter.EnderecoMapper;
import br.forsign.allo.entidade.model.EnderecoInput;
import br.forsign.allo.entidade.model.EnderecoOutput;
import br.forsign.allo.entidade.service.action.EnderecoCreator;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

/**
 * @author kaiooliveira
 * created 16/06/2024
 */

@Service
@CommonsLog
public class EnderecoService {

    @Resource
    private EnderecoCreator creator;

    @Resource
    private EnderecoMapper mapper;

    public EnderecoOutput create(EnderecoInput input){
        log.info("Iniciando criação de endereço.");

        return mapper.toOutput(creator.create(input));
    }
}
