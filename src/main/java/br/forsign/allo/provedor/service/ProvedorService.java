package br.forsign.allo.provedor.service;


import br.forsign.allo.avaliacao.repository.AvaliacaoRepository;
import br.forsign.allo.cliente.service.actions.ClienteGetter;
import br.forsign.allo.provedor.converter.ProvedorConverter;
import br.forsign.allo.provedor.converter.ProvedorMapper;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.ProvedorCadastroInput;
import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.provedor.model.ProvedorOutput;
import br.forsign.allo.provedor.service.action.ProvedorCreator;
import br.forsign.allo.provedor.service.action.ProvedorDeleter;
import br.forsign.allo.provedor.service.action.ProvedorGetter;
import br.forsign.allo.provedor.service.action.ProvedorUpdater;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
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

    @Resource
    private AvaliacaoRepository avaliacaoRepository;

    @Transactional
    public ProvedorOutput findById(Long id) {
        log.info("Iniciando consulta provedor pelo id.");

        return converter.toOutput(getter.byId(id));
    }

    @Transactional
    public List<ProvedorOutput> findByProfissao(Long idProfissao) {
        return this.getter.byProfissao(idProfissao).stream().map(mapper::toOutput).toList();
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
    public ProvedorOutput create(ProvedorCadastroInput input) {
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

    @Transactional
    public int getTotalAval(Long id){
//        return avaliacaoRepository.byProvedor(id).size();
        return 1;
    }

    public ResponseEntity<org.springframework.core.io.Resource> getImage(String filename){
        return getter.getImageByName(filename);
    }

    public String postImage(MultipartFile file){
        return updater.postImagemProvedor(file);
    }

}
