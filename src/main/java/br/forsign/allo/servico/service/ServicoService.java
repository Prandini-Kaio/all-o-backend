package br.forsign.allo.servico.service;

import br.forsign.allo.servico.converter.ServicoMapper;
import br.forsign.allo.servico.model.ServicoInput;
import br.forsign.allo.servico.model.ServicoOutput;
import br.forsign.allo.servico.service.action.ServicoUpdater;
import br.forsign.allo.servico.service.action.ServicoCreator;
import br.forsign.allo.servico.service.action.ServicoGetter;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(ServicoService.class);

    public ServicoOutput requisitarServico(Long idProvedor){

        logger.info("Iniciando requisição de serviço para o provedor: {}", idProvedor);

        return this.mapper.toOutput(this.creator.abrirServico(idProvedor));
    }

    public ServicoOutput confirmarServico(Long idServico, Boolean confirmado) {
        logger.info("Iniciando confirmação ( {} ) de serviço: {}", confirmado, idServico);

        return this.mapper.toOutput(this.updater.confirmarServico(idServico, confirmado));
    }

    public ServicoOutput avaliarServico(ServicoInput input) {
        logger.info("Iniciando avaliação de serviço: {}", input.getId());

        return this.mapper.toOutput(this.updater.avaliarServico(input));
    }

    public List<ServicoOutput> findByNaoVistoPeloProvedor() {
        logger.info("Iniciando busca de serviços não vistos pelo provedor");

        return this.getter.byNaoVistoPeloProvedor().stream().map(mapper::toOutput).toList();
    }

    public List<ServicoOutput> findByNaoVistoPeloCliente() {
        logger.info("Iniciando busca de serviços não vistos pelo cliente");

        return this.getter.byNaoVistoPeloCliente().stream().map(mapper::toOutput).toList();
    }

    public List<ServicoOutput> findByProvedor(Long idProvedor) {

        logger.info("Iniciando busca de serviços pelo provedor: {}", idProvedor);

        return this.getter.byProvedor(idProvedor).stream().map(mapper::toOutput).toList();
    }
}
