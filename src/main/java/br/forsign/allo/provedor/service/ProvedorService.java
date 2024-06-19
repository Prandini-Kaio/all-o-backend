package br.forsign.allo.provedor.service;


import br.forsign.allo.cliente.service.actions.ClienteGetter;
import br.forsign.allo.provedor.converter.ProvedorConverter;
import br.forsign.allo.provedor.converter.ProvedorMapper;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.provedor.model.ProvedorOutput;
import br.forsign.allo.provedor.service.action.ProvedorCreator;
import br.forsign.allo.provedor.service.action.ProvedorDeleter;
import br.forsign.allo.provedor.service.action.ProvedorGetter;
import br.forsign.allo.provedor.service.action.ProvedorUpdater;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@CommonsLog
public class ProvedorService {

    @Resource
    private ProvedorCreator creator;

    @Resource
    private ProvedorGetter getter;

    @Resource
    private ProvedorUpdater updater;

    @Resource
    private ProvedorDeleter deleter;

    @Resource
    private ProvedorMapper mapper;

    @Resource
    private ProvedorConverter converter;

    @Resource
    private ClienteGetter clienteGetter;

    @Transactional
    public ProvedorOutput findById(Long id) {
        log.info("Iniciando consulta provedor pelo id.");

        return converter.toOutput(getter.byId(id));
    }

    @Transactional
    public Page<ProvedorOutput> findByProfissao(Long idProfissao, Pageable pageable) {
        return this.getter.byProfissao(idProfissao, pageable).map(mapper::toOutput);
    }

    @Transactional
    public Page<ProvedorOutput> findAllComFavoritos(Long idCliente, Pageable pageable) {
        log.info("Iniciando consulta todos os provedores do sistema.");

        Page<Provedor> provedores = getter.findAll(pageable);

        // Id dos provedores favoritos
        Set<Long> favIDs = clienteGetter.byId(idCliente)
                .getProvedoresFavoritados()
                .stream()
                .map(Provedor::getId)
                .collect(Collectors.toSet());

        // MAPEAR PARA OUTPUT
        return provedores.map(p -> {
            ProvedorOutput output = mapper.toOutput(p);
            output.setFavorito(favIDs.contains(p.getId()));
            return output;
        });
    }

    @Transactional
    public Page<ProvedorOutput> findByFilter(String nome, String profissao, Pageable pageable) {
        log.info("Iniciando consulta de provedores com filtro.");

        return getter.findByFilter(nome, profissao, pageable).map(mapper::toOutput);
    }

    @Transactional
    public ProvedorOutput create(ProvedorInput input) {
        log.info("Cadastrando provedor no sistema.");

        return converter.toOutput(creator.create(input));
    }

    @Transactional
    public ProvedorOutput update(ProvedorInput input){
        log.info("Atualizando provedor no sistema.");

        return mapper.toOutput(updater.update(input));
    }

    @Transactional
    public void delete(Long id){
        log.info("Deletando provedor no sistema.");

        deleter.byId(id);
    }
}
