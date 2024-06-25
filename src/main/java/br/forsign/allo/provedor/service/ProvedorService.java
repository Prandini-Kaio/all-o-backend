package br.forsign.allo.provedor.service;


import br.forsign.allo.avaliacao.repository.AvaliacaoRepository;
import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.service.actions.ClienteGetter;
import br.forsign.allo.provedor.converter.PerfilProvedorMapper;
import br.forsign.allo.provedor.converter.ProvedorConverter;
import br.forsign.allo.provedor.converter.ProvedorMapper;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.domain.TipoUpload;
import br.forsign.allo.provedor.model.PerfilProvedorOutput;
import br.forsign.allo.provedor.model.ProvedorCadastroInput;
import br.forsign.allo.provedor.model.ProvedorDestaquesOutput;
import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.provedor.model.ProvedorListOutput;
import br.forsign.allo.provedor.model.ProvedorOutput;
import br.forsign.allo.provedor.service.action.ProvedorCreator;
import br.forsign.allo.provedor.service.action.ProvedorDeleter;
import br.forsign.allo.provedor.service.action.ProvedorGetter;
import br.forsign.allo.provedor.service.action.ProvedorUpdater;
import br.forsign.allo.provedor.service.action.perfil.PerfilProvedorGetter;
import br.forsign.allo.usuario.domain.Usuario;
import br.forsign.allo.usuario.domain.UsuarioRole;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Resource
    private PerfilProvedorMapper perfilProvedorMapper;

    @Resource
    private PerfilProvedorGetter perfilProvedorGetter;

    @Transactional
    public ProvedorOutput findById(Long id) {
        log.info("Iniciando consulta provedor pelo id.");

        return makeProvedorOutput(getter.byId(id));
    }

    @Transactional
    public List<ProvedorListOutput> findByProfissao(Long idProfissao) {
        return makeProvedorListOutput(this.getter.byProfissao(idProfissao));
    }

    @Transactional
    public Page<ProvedorOutput> findAllComFavoritos(Pageable pageable) {
        log.info("Iniciando consulta todos os provedores do sistema.");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (Usuario) authentication.getPrincipal();

        Page<Provedor> provedores = getter.findAll(pageable);

        // Id dos provedores favoritos
        Set<Long> favIDs = clienteGetter.byUsername(userDetails.getUsername())
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

        return getter.findByFilter(nome, profissao, pageable).map(this::makeProvedorOutput);
    }

    @Transactional
    public ProvedorOutput create(ProvedorCadastroInput input) {
        log.info("Cadastrando provedor no sistema.");

        return converter.toOutput(creator.create(input));
    }

    @Transactional
    public ProvedorOutput update(ProvedorInput input) {
        log.info("Atualizando provedor no sistema.");

        return mapper.toOutput(updater.update(input));
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deletando provedor no sistema.");

        deleter.byId(id);
    }

    @Transactional
    public ResponseEntity<org.springframework.core.io.Resource> getImage(String filename, TipoUpload tipo) {
        if (tipo == TipoUpload.PERFIL)
            return getter.getImageByName(filename, "images-provedor");
        else
            return getter.getImageByName(filename, "images-perfil-provedor");
    }

    @Transactional
    public String postImage(MultipartFile file, TipoUpload tipo) {
        return updater.postImagemProvedor(file, tipo);
    }

    public List<ProvedorDestaquesOutput> getByHighAvaliacao(){
        return converter.toDestaqueOutput(getter.getByHighAvaliacao());
    }

    private ProvedorOutput makeProvedorOutput(Provedor provedor){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) authentication.getPrincipal();

        if (!usuario.getRole().equals(UsuarioRole.CLIENTE))
            return mapper.toOutput(provedor);


        Cliente cliente = clienteGetter.byUsername(usuario.getUsername());

        Set<Provedor> provedores = cliente.getProvedoresFavoritados();

        ProvedorOutput provedorOutput = mapper.toOutput(provedor);

        if (provedores.contains(provedor))
            provedorOutput.setFavorito(true);

        return provedorOutput;
    }

    private ProvedorListOutput makeProvedorListOutput(Provedor provedor) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) authentication.getPrincipal();

        Cliente cliente = clienteGetter.byUsername(usuario.getUsername());
        Set<Provedor> provedores = cliente.getProvedoresFavoritados();

        PerfilProvedorOutput perfilProvedorOutput = perfilProvedorMapper.toOutput(perfilProvedorGetter.byProvedorId(provedor.getId()));
        ProvedorListOutput provedorListOutput = new ProvedorListOutput(perfilProvedorOutput);

        if (provedores.contains(provedor))
            provedorListOutput.setFavorito(true);

        return provedorListOutput;
    }

    private List<ProvedorListOutput> makeProvedorListOutput(List<Provedor> provedores) {
        return provedores.stream().map(this::makeProvedorListOutput).toList();
    }

    private List<ProvedorOutput> makeProvedorOutput(List<Provedor> provedores) {
        return provedores.stream().map(this::makeProvedorOutput).toList();
    }

}
