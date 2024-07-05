package br.forsign.allo.servico.service;

import br.forsign.allo.servico.converter.ServicoMapper;
import br.forsign.allo.servico.model.ServicoInput;
import br.forsign.allo.servico.model.ServicoOutput;
import br.forsign.allo.servico.service.action.ServicoUpdater;
import br.forsign.allo.servico.service.action.ServicoCreator;
import br.forsign.allo.servico.service.action.ServicoGetter;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kaiooliveira
 * created 23/06/2024
 */

@Service
@CommonsLog
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

        log.info(String.format("Iniciando requisição de serviço para o provedor: %s", idProvedor));

        return this.mapper.toOutput(this.creator.abrirServico(idProvedor));
    }

    public ServicoOutput confirmarServico(Long idServico, Boolean confirmado) {
        log.info(String.format("Iniciando confirmação de serviço ( %s ) de serviço com ID: %s", confirmado, idServico));

        return this.mapper.toOutput(this.updater.confirmarServico(idServico, confirmado));
    }

    public ServicoOutput avaliarServico(ServicoInput input) {
        log.info(String.format("Iniciando avaliação de serviço: %s", input.getId()));

        return this.mapper.toOutput(this.updater.avaliarServico(input));
    }

    public List<ServicoOutput> findByNaoVistoPeloProvedor() {
        log.info("Iniciando busca de serviços não vistos pelo provedor");

        return this.getter.byNaoVistoPeloProvedor().stream().map(mapper::toOutput).toList();
    }

    public List<ServicoOutput> findByNaoVistoPeloCliente() {
        log.info("Iniciando busca de serviços não vistos pelo cliente");

        return this.getter.byNaoVistoPeloCliente().stream().map(mapper::toOutput).toList();
    }

    public List<ServicoOutput> findByProvedor(Long idProvedor) {

        log.info(String.format("Iniciando busca de serviços pelo provedor %s", idProvedor));

        return this.getter.byProvedor(idProvedor).stream().map(mapper::toOutput).toList();
    }
}
