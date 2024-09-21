package br.forsign.allo.provedor.service;


import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.service.actions.ClienteGetter;
import br.forsign.allo.provedor.converter.PerfilProvedorMapper;
import br.forsign.allo.provedor.converter.ProvedorConverter;
import br.forsign.allo.provedor.converter.ProvedorMapper;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.domain.TipoUpload;
import br.forsign.allo.provedor.model.*;
import br.forsign.allo.provedor.service.action.ProvedorCreator;
import br.forsign.allo.provedor.service.action.ProvedorDeleter;
import br.forsign.allo.provedor.service.action.ProvedorGetter;
import br.forsign.allo.provedor.service.action.ProvedorUpdater;
import br.forsign.allo.provedor.service.action.perfil.PerfilProvedorGetter;
import br.forsign.allo.usuario.domain.Usuario;
import br.forsign.allo.usuario.domain.UsuarioRole;
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
import org.springframework.web.multipart.MultipartFile;

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

    @Resource
    private PerfilProvedorMapper perfilProvedorMapper;

    @Resource
    private PerfilProvedorGetter perfilProvedorGetter;

    @Transactional
    public ProvedorOutput findById(Long id) {
        log.info(String.format("Iniciando consulta provedor pelo ID %s", id));

        return makeProvedorOutput(getter.byId(id));
    }

    @Transactional
    public List<ProvedorListOutput> findByProfissao(Long idProfissao) {
        log.info(String.format("Iniciando consulta a provedor pelo ID da profissão %s", idProfissao));

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
        log.info(String.format("Iniciando consulta de provedores com filtro %s;\n %s.", nome, profissao));

        return getter.findByFilter(nome, profissao, pageable).map(this::makeProvedorOutput);
    }

    @Transactional
    public List<ProvedorOutput> findByFilter(ProvedorFilter filter) {
        log.info(String.format("Iniciando consulta de provedores com filtro "));

        return getter.findByFilter(filter).stream().map(this::makeProvedorOutput).toList();
    }

    @Transactional
    public ProvedorOutput create(ProvedorCadastroInput input) {
        log.info(String.format("Cadastrando provedor %s no sistema.", input.getProvedor().getRazaoSocial()));

        return converter.toOutput(creator.create(input));
    }

    @Transactional
    public ProvedorOutput update(ProvedorInput input) {
        log.info("Atualizando provedor no sistema.");

        return mapper.toOutput(updater.update(input));
    }

    @Transactional
    public void delete(Long id) {
        log.info(String.format("Deletando provedor com ID %s no sistema.", id));

        deleter.byId(id);
    }

    @Transactional
    public ResponseEntity<org.springframework.core.io.Resource> getImage(String filename, TipoUpload tipo) {
        log.info(String.format("Iniciando consulta de imagem de provedor [%s]", tipo));

        if (tipo == TipoUpload.PERFIL)
            return getter.getImageByName(filename, "images-provedor");
        else
            return getter.getImageByName(filename, "images-perfil-provedor");
    }

    @Transactional
    public String postImage(MultipartFile file, TipoUpload tipo) {
        log.info(String.format("Iniciando cadastro de imagem de provedor [%s]", tipo));

        return updater.postImagemProvedor(file, tipo);
    }

    public List<ProvedorDestaquesOutput> getByHighAvaliacao(){
        log.info("Iniciando consulta a profissionais em destaque.");

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

    public List<ProvedorOutput> getByMostRelevant(){
        log.info("Iniciando consulta a profissionais mais relevantes.");

        return this.getter.mostRelevant().stream().map(this::makeProvedorOutput).toList();
    }
}
