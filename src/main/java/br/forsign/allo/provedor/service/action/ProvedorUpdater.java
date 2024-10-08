package br.forsign.allo.provedor.service.action;

import br.forsign.allo.common.utils.ImageUtils;
import br.forsign.allo.entidade.converter.EnderecoMapper;
import br.forsign.allo.importacao.service.StorageService;
import br.forsign.allo.profissao.domain.Profissao;
import br.forsign.allo.profissao.service.action.ProfissaoGetter;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.domain.TipoUpload;
import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.provedor.repository.ProvedorRepository;
import br.forsign.allo.provedor.service.ProvedorValidator;
import br.forsign.allo.provedor.service.action.perfil.PerfilProvedorGetter;
import br.forsign.allo.provedor.service.action.perfil.PerfilProvedorUpdater;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;

@Component
@CommonsLog
public class ProvedorUpdater {

    @Resource
    private ProvedorRepository repository;

    @Resource
    private ProvedorGetter getter;

    @Resource
    private ProvedorValidator validator;

    @Resource
    private PerfilProvedorUpdater perfilProvedorUpdater;

    @Resource
    private PerfilProvedorGetter perfilProvedorGetter;

    @Resource
    private ProfissaoGetter profissaoGetter;

    @Resource
    private EnderecoMapper enderecoMapper;

    @Resource
    private StorageService storageService;

    public Provedor update(ProvedorInput input) {
        log.info(String.format("Atualizando provedor %s", input.getRazaoSocial()));

        validator.validarUpdate(input);

        Profissao profissao = profissaoGetter.byIdAtivo(input.getIdProfissao());

        Provedor provedor = getter.byId(input.getId());

        provedor.setRazaoSocial(input.getRazaoSocial());
        provedor.setTipoPessoa(input.getTipoPessoa());

        provedor.setEmail(input.getEmail());
        provedor.setTelefone(input.getTelefone());
        provedor.setCpfCnpj(input.getCpfCnpj());
        provedor.setEndereco(enderecoMapper.fromInput(input.getEnderecoInput()));
        provedor.setProfissao(profissao);

        perfilProvedorUpdater.update(input);

        return repository.save(provedor);
    }

    public String uploadImage(MultipartFile file, TipoUpload tipoUpload, Long idProvedor) {
        log.info(String.format("Atualizando imagem do provedor, tipo [%s].", tipoUpload));

        String path = "provedor"
                .concat(File.separator).concat(idProvedor.toString())
                .concat(File.separator).concat(tipoUpload.equals(TipoUpload.PERFIL) ? "perfil" : "servicos");

        String filename = file.getOriginalFilename();

        if (tipoUpload.equals(TipoUpload.PERFIL)) {
            String[] splittedFilename = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");

            filename = file.getOriginalFilename().replace(splittedFilename[0], "perfil");
        }

        String url = this.storageService.upload(file, path, filename);

        Provedor provedor = getter.byId(idProvedor);
        provedor.getPerfilProvedor().setImagemPerfil(url);

        repository.save(provedor);
        return url;
    }
}
