package br.forsign.allo.servico.service;

import br.forsign.allo.servico.converter.ServicoMapper;
import br.forsign.allo.servico.model.ServicoInput;
import br.forsign.allo.servico.model.ServicoOutput;
import br.forsign.allo.servico.service.action.ServiceUpdater;
import br.forsign.allo.servico.service.action.ServicoCreator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author kaiooliveira
 * created 23/06/2024
 */

@Service
public class ServicoService {

    @Resource
    private ServicoCreator creator;

    @Resource
    private ServiceUpdater updater;

    @Resource
    private ServicoMapper mapper;

    public ServicoOutput requisitarServico(Long idProvedor){
        return this.mapper.toOutput(this.creator.abrirServico(idProvedor));
    }

    public ServicoOutput update(ServicoInput input){
        return this.mapper.toOutput(this.updater.update(input));
    }
}
