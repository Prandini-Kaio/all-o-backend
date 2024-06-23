package br.forsign.allo.provedor.service.action;

import br.forsign.allo.common.utils.ImageUtils;
import br.forsign.allo.entidade.converter.EnderecoMapper;
import br.forsign.allo.profissao.domain.Profissao;
import br.forsign.allo.profissao.service.action.ProfissaoGetter;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.provedor.repository.ProvedorRepository;
import br.forsign.allo.provedor.service.ProvedorValidator;
import br.forsign.allo.provedor.service.action.perfil.PerfilProvedorUpdater;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
    private ProfissaoGetter profissaoGetter;

    @Resource
    private EnderecoMapper enderecoMapper;

    public Provedor update(ProvedorInput input){
        validator.validarUpdate(input);

        List<Profissao> profissoes = input.getIdProfissoes().stream()
                .map(profissaoGetter::byIdAtivo)
                .toList();

        Provedor provedor = getter.byId(input.getId());

        provedor.setRazaoSocial(input.getRazaoSocial());
        provedor.setTipoPessoa(input.getTipoPessoa());

        provedor.setEmail(input.getEmail());
        provedor.setTelefone(input.getTelefone());
        provedor.setCpfCnpj(input.getCpfCnpj());
        provedor.setEndereco(enderecoMapper.fromInput(input.getEnderecoInput()));
        provedor.setProfissoes(profissoes);

        perfilProvedorUpdater.update(input);

        return repository.save(provedor);
    }

    public String postImagemProvedor(MultipartFile file) {
        return ImageUtils.saveImageFile(file, "images-provedor");
    }
}
