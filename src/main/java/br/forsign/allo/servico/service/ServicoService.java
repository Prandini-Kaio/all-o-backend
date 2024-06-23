package br.forsign.allo.servico.service;

import br.forsign.allo.servico.converter.ServicoMapper;
import br.forsign.allo.servico.model.ServicoInput;
import br.forsign.allo.servico.model.ServicoOutput;
import br.forsign.allo.servico.service.action.ServicoUpdater;
import br.forsign.allo.servico.service.action.ServicoCreator;
import br.forsign.allo.servico.service.action.ServicoGetter;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kaiooliveira
 * created 23/06/2024
 */

@Service
public class ServicoService {

    @Resource
    private ServicoCreator creator;

    @Resource
    private ServicoUpdater updater;

    @Resource
    private ServicoGetter getter;

    @Resource
    private ServicoMapper mapper;

    public ServicoOutput requisitarServico(Long idProvedor){
        return this.mapper.toOutput(this.creator.abrirServico(idProvedor));
    }

    public ServicoOutput confirmarServico(Long idServico, Boolean confirmado) {
        return this.mapper.toOutput(this.updater.confirmarServico(idServico, confirmado));
    }

    public ServicoOutput avaliarServico(ServicoInput input) {
        return this.mapper.toOutput(this.updater.avaliarServico(input));
    }

    public List<ServicoOutput> findByNaoVistoPeloProvedor() {
        return this.getter.byNaoVistoPeloProvedor().stream().map(mapper::toOutput).toList();
    }

    public List<ServicoOutput> findByNaoVistoPeloCliente() {
        return this.getter.byNaoVistoPeloCliente().stream().map(mapper::toOutput).toList();
    }
}
