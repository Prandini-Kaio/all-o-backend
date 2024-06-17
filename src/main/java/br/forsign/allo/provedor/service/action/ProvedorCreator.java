package br.forsign.allo.provedor.service.action;

import br.forsign.allo.common.utils.CpfCnpjUtils;
import br.forsign.allo.entidade.converter.EnderecoMapper;
import br.forsign.allo.entidade.service.action.EnderecoCreator;
import br.forsign.allo.profissao.domain.Profissao;
import br.forsign.allo.profissao.service.action.ProfissaoGetter;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.provedor.repository.ProvedorRepository;
import br.forsign.allo.provedor.service.PerfilProvedorService;
import br.forsign.allo.provedor.service.ProvedorValidator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ProvedorCreator {

    @Resource
    private ProvedorRepository repository;

    @Resource
    private ProvedorValidator validator;

    @Resource
    private PerfilProvedorService perfilProvedorService;

    @Resource
    private ProfissaoGetter profissaoGetter;

    @Resource
    private EnderecoCreator enderecoCreator;

    public Provedor create(ProvedorInput input){
        validator.validarCreate(input);

        Provedor provedor = new Provedor();

        List<Profissao> profissao = profissaoGetter.byIdAtivo(input.getIdProfissoes());

        provedor.setRazaoSocial(input.getRazaoSocial());
        provedor.setEndereco(enderecoCreator.create(input.getEnderecoInput()));
        provedor.setTipoPessoa(input.getTipoPessoa());
        provedor.setEmail(input.getEmail());
        provedor.setTelefone(input.getTelefone());
        provedor.setCpfCnpj(CpfCnpjUtils.removeMascara(input.getCpfCnpj()));
        provedor.setProfissoes(profissao);
        provedor.setAtivo(true);
        provedor.setDtRegistro(LocalDate.now());

        repository.save(provedor);

        perfilProvedorService.create(input, provedor);

        return provedor;
    }
}
