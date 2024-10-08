package br.forsign.allo.provedor.service.action;

import br.forsign.allo.common.file.exceptions.FileException;
import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.importacao.service.StorageService;
import br.forsign.allo.importacao.service.files.FileService;
import br.forsign.allo.provedor.domain.PerfilProvedor;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.ProvedorFilter;
import br.forsign.allo.provedor.repository.ProvedorRepository;
import br.forsign.allo.provedor.service.action.perfil.PerfilProvedorGetter;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Component
@CommonsLog
public class ProvedorGetter {

    @Resource
    private ProvedorRepository repository;

    @Resource
    private PerfilProvedorGetter perfilProvedorGetter;

    @Resource
    private StorageService storageService;

    @Resource
    private FileService fileService;

    public Page<Provedor> findAll(Pageable pageable) {
        log.info("Consultando todos os provedores.");

        return repository.findAtivos(pageable).orElseThrow(CommonExceptionSupplier.naoEncontrado("Provedor"));
    }

    public Provedor byId(Long id){
        log.info(String.format("Consultando provedor com ID %s.", id));

        return repository
                .findById(id)
                .orElseThrow(CommonExceptionSupplier.naoEncontrado("Provedor", id));
    }

    public Page<Provedor> findByFilter(String razaoSocial, String profissao, Pageable pageable){
        log.info(String.format("Consultando provedor pelo nome %s; profissão %s.", razaoSocial, profissao));

        return repository.byFilter(razaoSocial, profissao, pageable);
    }

    public List<Provedor> findByFilter(ProvedorFilter filter){
        log.info(String.format("Consultando provedor pelo nome"));

        return repository.byFilter(filter);
    }

    public Provedor byCpfCnpj(String cpfCnpj){
        log.info(String.format("Consultando provedor pelo CPF/CNPJ %s.", cpfCnpj));

        return repository.findByCpfCnpj(cpfCnpj).orElseThrow(CommonExceptionSupplier.naoEncontrado("Provedor", cpfCnpj));
    }

    public boolean existsByCpfCnpj(String cpfCnpj){
        log.info(String.format("Consultando a existência de provedor com base no CPF/CNPJ %s", cpfCnpj));

        Optional<Provedor> provedor = repository.findByCpfCnpj(cpfCnpj);

        return provedor.isPresent();
    }

    public boolean existsById(Long id){
        log.info(String.format("Consultando a existência de provedor com base no ID %s", id));

        Optional<Provedor> provedor = repository.findById(id);

        return provedor.isPresent();
    }

    public List<Provedor> byProfissao(Long idProfissao) {
        log.info(String.format("Consultando provedor com base no ID da profissão %s", idProfissao));

        return repository.findByProfissoesId(idProfissao);
    }

    public MultipartFile findPerfilImage(Long idProvedor) throws IOException, FileException {
        log.info(String.format("Bucando imagem de perfil do provedor %s", idProvedor));
        PerfilProvedor perfilProvedor = perfilProvedorGetter.byProvedorId(idProvedor);

        return storageService.download(perfilProvedor.getImagemPerfil());
    }

    public List<Provedor> getByHighAvaliacao() {
        log.info("Consulta profissionais em destaque.");

        return repository.findMelhoresAvaliacoes();
    }

    public List<Provedor> mostRelevant(){
        log.info("Consulta profissionais mais relevantes.");

        return repository.mostRelevant();
    }

    public Provedor byUsername(String username) {
        log.info(String.format("Consultando provedor com base no username %s", username));

        return repository.byUsername(username).orElseThrow(CommonExceptionSupplier.naoEncontrado("Provedor", username));
    }
}
